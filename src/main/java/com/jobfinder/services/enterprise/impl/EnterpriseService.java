package com.jobfinder.services.enterprise.impl;

import com.jobfinder.dto.enterprise.EnterpriseRequest;
import com.jobfinder.dto.enterprise.UpdateEnterpriseRequest;
import com.jobfinder.dto.user.RegisterUserRequest;
import com.jobfinder.entities.enterprise.Enterprise;
import com.jobfinder.entities.finder.Finder;
import com.jobfinder.entities.user.UserJobFinder;
import com.jobfinder.exception.OperationNonPermittedException;
import com.jobfinder.repositories.enterprise.EnterpriseRepository;
import com.jobfinder.repositories.finder.FinderRepository;
import com.jobfinder.services.enterprise.IEnterpriseService;
import com.jobfinder.services.user.IUserJobFinderDetailsService;
import com.jobfinder.services.utils.GenerateCodeUtils;
import com.jobfinder.validator.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseService implements IEnterpriseService {

    private ObjectValidator<EnterpriseRequest> registerValidator;

    private ObjectValidator<UpdateEnterpriseRequest> updateValidator;

    private EnterpriseRepository enterpriseRepository;

    private IUserJobFinderDetailsService userService;

    private GenerateCodeUtils codeUtils;

    private FinderRepository finderRepository;


    @Override
    @Transactional
    public Enterprise addEnterprise(EnterpriseRequest request) {
        try{
            registerValidator.validate(request);
            UserJobFinder user = userService.register(
                    RegisterUserRequest.builder()
                            .isEnterprise(true)
                            .username(request.getEmail())
                            .password(request.getPass())
                            .cPassword(request.getCPass())
                            .isSuperAdmin(false)
                            .build()
            );
            //TODO: Implémenter l'envoi d'email d'activation de code
            return enterpriseRepository.save(
                    Enterprise.builder()
                            .enterpriseCode(codeUtils.generateEnterpriseCode())
                            .enterpriseEmail(request.getEmail())
                            .enterpriseName(request.getName())
                            .description(request.getDescription())
                            .sector(request.getSector())
                            .siteLink(request.getSiteLink())
                            .telephone(request.getTelephone())
                            .profil(request.getLogo().getBytes())
                            .user(user)
                            .build()
            );
        }catch (IOException e){
            throw new OperationNonPermittedException("Erreur lors du chargement du logo");
        }
    }

    @Override
    public Enterprise updateEnterprise(Integer enterpriseId, UpdateEnterpriseRequest request) {
            updateValidator.validate(request);
            Enterprise enterprise = enterpriseRepository.findByEnterpriseId(enterpriseId).orElseThrow(() -> new EntityNotFoundException("Entreprise introuvable"));

            enterprise.setEnterpriseName(request.getName());
            enterprise.setDescription(request.getDescription());
            enterprise.setSector(request.getSector());
            enterprise.setTelephone(request.getTelephone());
            enterprise.setSiteLink(request.getSiteLink());

            return enterpriseRepository.save(enterprise);
    }

    @Override
    public List<Enterprise> findEnterprisesBySector(Integer finderId) {
        Finder finder = finderRepository.findByFinderId(finderId).orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable"));
        return enterpriseRepository.findAllBySector(finder.getSector());
    }

    @Override
    public void addEnterpriseLogo( Integer enterpriseId, MultipartFile logo){
        try{
            Enterprise enterprise = enterpriseRepository.findByEnterpriseId(enterpriseId).orElseThrow(() -> new EntityNotFoundException("Entreprise introuvable"));
            enterprise.setProfil(logo.getBytes());

        }catch(IOException e){
            throw new OperationNonPermittedException("Erreur lors du chargement du logo");
        }
    }


}
