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
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserJobFinderDetailsService implements IUserJobFinderDetailsService {

    private UserJobFinderRepository userJobFinderRepository;

    private ObjectValidator<RegisterUserRequest> validatorFinder;

    private BCryptPasswordEncoder passwordEncoder;

    private ObjectValidator<LoginRequest> authValidator;

    private ObjectValidator<ChangePasswordRequest> changePasswordValidator;

    private AuthenticationManager authManager;

    private GenerateCodeUtils generateCodeUtils;

    private JwtUtil jwtUtil;

    private FinderRepository finderRepository;

    private static final Logger log = LoggerFactory.getLogger(UserJobFinderDetailsService.class);

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
            throw new OperationNonPermittedException("La confirmation du mot de passe est incorrecte");
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
    @Transactional
    public Void resetPassword(String email) {
        UserJobFinder user = userJobFinderRepository.findUserJobFinderByUsername(email);
        if (user == null){
            throw new EntityNotFoundException("Aucun compte ne correspond à cette adresse");
        }
        String newPwdGenerate = generateCodeUtils.generateUserStrongPasswordReset(user);
        log.info(newPwdGenerate);
        user.setPwd(passwordEncoder.encode(newPwdGenerate));
        userJobFinderRepository.save(user);
        //TODO
        //Implementer l'envoie de l'email contenant le nouveau mot de passe
        return null;
    }

    @Override
    @Transactional
    public Void changePassword(Integer accountId, ChangePasswordRequest request) {
        changePasswordValidator.validate(request);
        ChangePasswordRequest.checkPasswordConfimation(request.getNewPassword(), request.getCNewPassword());
        UserJobFinder user = userJobFinderRepository.findByAccountId(accountId).orElseThrow(() -> new EntityNotFoundException("Compte utilisateur inconnu"));
        if (!passwordEncoder.matches(request.getLastPassword(), user.getPwd())){
            throw new OperationNonPermittedException("Mot de passe incorrect");
        }
        user.setPwd(passwordEncoder.encode(request.getNewPassword()));
        userJobFinderRepository.save(user);
        return null;
    }

    @Override
    @Transactional
    public Void activateAccount(Integer accountId, Integer activationCode) {
        UserJobFinder user = userJobFinderRepository.findByAccountId(accountId).orElseThrow(() -> new EntityNotFoundException("Compte utilisateur inconnu"));

        Instant currentDate = Instant.now();
        Instant deadLine = Instant.parse(user.getCreatedAt().toString()).plusSeconds(24 * 3600);
        if (!user.getActivationCode().equals(activationCode)){
            throw new OperationNonPermittedException("Opération échouée, code d'activation incorrect");
        }else if (currentDate.isAfter(deadLine)) {
            throw new OperationNonPermittedException("Ce code d'activation n'est plus valide, réclamez en un nouveau");
        }
        user.setActive(true);
        userJobFinderRepository.save(user);
        return null;
    }

    @Override
    public Void deactivateAccountWithId(Integer accountId) {
        return null;
    }

    @Override
    @Transactional
    public Void deactivateAccountWithEmail(String email) {
        UserJobFinder user = userJobFinderRepository.findUserJobFinderByUsername(email);
        if (user == null) {
            throw new EntityNotFoundException("Aucun compte ne correspond à l'adresse entrée");
        }
        user.setActive(false);
        userJobFinderRepository.save(user);
        return null;
    }

}
