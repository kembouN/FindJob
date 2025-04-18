package com.jobfinder.services.user.impl;

import com.jobfinder.config.JwtUtil;
import com.jobfinder.dto.auth.LoginResponse;
import com.jobfinder.dto.user.ChangePasswordRequest;
import com.jobfinder.dto.user.UserDto;
import com.jobfinder.dto.auth.LoginRequest;
import com.jobfinder.dto.user.RegisterUserRequest;
import com.jobfinder.entities.finder.Finder;
import com.jobfinder.entities.user.UserJobFinder;
import com.jobfinder.exception.OperationNonPermittedException;
import com.jobfinder.repositories.finder.FinderRepository;
import com.jobfinder.repositories.user.UserJobFinderRepository;
import com.jobfinder.services.user.IUserJobFinderDetailsService;
import com.jobfinder.services.utils.GenerateCodeUtils;
import com.jobfinder.validator.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserJobFinderDetailsService implements IUserJobFinderDetailsService {

    private UserJobFinderRepository userJobFinderRepository;

    private ObjectValidator<RegisterUserRequest> validatorFinder;

    private BCryptPasswordEncoder passwordEncoder;

    private ObjectValidator<LoginRequest> authValidator;

    private AuthenticationManager authManager;

    private GenerateCodeUtils generateCodeUtils;

    private JwtUtil jwtUtil;

    private FinderRepository finderRepository;

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserJobFinder jobFinder = userJobFinderRepository.findUserJobFinderByUsername(username);

        if (jobFinder != null) {
            UserDetails userDetails = User.withUsername(jobFinder.getUsername())
                    .password(jobFinder.getPwd())
                    .build();
            return userDetails;
        }
        return null;
    }

     */

    @Override
    public LoginResponse login(LoginRequest request) {
        authValidator.validate(request);
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        UserJobFinder user =  userJobFinderRepository.findUserJobFinderByUsername(request.getUsername());
        if (!user.isActive()){
            throw new OperationNonPermittedException("Veuillez activer votre compte ou contactez les administrateurs");
        }
        user.setLastConnection(LocalDate.now());
        userJobFinderRepository.save(user);

        Finder finder = finderRepository.findByUser(user);

        if (finder != null){
            UserDto userDto = new UserDto(
                    user.getUsername(),
                    LocalDate.now(),
                    user.isActive(),
                    user.isEnterprise(),
                    finder.getFinderCode(),
                    finder.getNom(),
                    finder.getPrenom(),
                    finder.getEmail(),
                    finder.getDateNaissance(),
                    finder.getSexe(),
                    finder.getTelephone()
            );
            return LoginResponse.builder()
                    .token(jwtUtil.createJwtToken(user))
                    .user(userDto)
                    .build();
        }else {
            return LoginResponse.builder()
                    .user(null)
                    .token(jwtUtil.createJwtToken(user))
                    .build();
        }

    }

    @Override
    @Transactional
    public UserJobFinder register(RegisterUserRequest request) {
        validatorFinder.validate(request);
        if (!request.getPassword().equals(request.getCPassword())){
            throw new OperationNonPermittedException("La confirmation du mot de passe ne correspond pas");
        }

        if (userJobFinderRepository.findUserJobFinderByUsername(request.getUsername()) != null){
            throw new OperationNonPermittedException("L'adresse email est déjà utilisé");
        }

        UserJobFinder user = new UserJobFinder();
        user.setUsername(request.getUsername());
        user.setPwd(passwordEncoder.encode(request.getPassword()));
        user.setActivationCode(generateCodeUtils.generateActivationCode());
        user.setActive(request.getIsSuperAdmin() ? true : false);
        user.setSuperAdmin(request.getIsSuperAdmin());
        user.setEnterprise(request.isEnterprise());
        return userJobFinderRepository.save(user);
        //TODO
        //Implemeter l'envoi d'email à utilisateur pour activation du compte

    }

    @Override
    public UserDto resetPassword(Integer accountId) {
        return null;
    }

    @Override
    public UserDto changePassword(Integer accountId, ChangePasswordRequest request) {
        return null;
    }

    @Override
    public Void activateAccount(Integer accountId, Integer activationCode) {
        UserJobFinder user = userJobFinderRepository.findByAccountId(accountId).orElseThrow(() -> new EntityNotFoundException("Le compte utilisateur est introuvable"));

        if (user.getActivationCode() != activationCode){
            throw new OperationNonPermittedException("Opération échouée, code d'activation incorrect");
        }
        user.setActive(true);
        userJobFinderRepository.save(user);
        return null;
    }

}
