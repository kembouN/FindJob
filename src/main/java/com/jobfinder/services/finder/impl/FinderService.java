package com.jobfinder.services.finder.impl;

import com.jobfinder.dto.finder.FinderResponse;
import com.jobfinder.dto.finder.RegisterFinder;
import com.jobfinder.dto.finder.UpdateFinderRequest;
import com.jobfinder.dto.user.RegisterUserRequest;
import com.jobfinder.entities.finder.Finder;
import com.jobfinder.entities.job.Localisation;
import com.jobfinder.entities.user.UserJobFinder;
import com.jobfinder.repositories.finder.FinderRepository;
import com.jobfinder.repositories.job.LocalisationRepository;
import com.jobfinder.services.finder.IFinderService;
import com.jobfinder.services.user.IUserJobFinderDetailsService;
import com.jobfinder.services.utils.GenerateCodeUtils;
import com.jobfinder.validator.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FinderService implements IFinderService {

    private FinderRepository finderRepository;

    private LocalisationRepository localisationRepository;

    private GenerateCodeUtils code;

    private static final Logger log = LoggerFactory.getLogger(FinderService.class);

    private IUserJobFinderDetailsService userService;

    private ObjectValidator<RegisterFinder> finderValidator;

    private ObjectValidator<UpdateFinderRequest> updateFinderValidator;

    @Override
    @Transactional
    public Finder createFinder(RegisterFinder request) {
        finderValidator.validate(request);
        UserJobFinder user = userService.register(
                RegisterUserRequest.builder()
                        .cPassword(request.getCPassword())
                        .isSuperAdmin(false)
                        .isEnterprise(request.isEnterprise())
                        .password(request.getPassword())
                        .username(request.getUsername())
                        .build()
        );


        Finder finder = finderRepository.save(
                Finder.builder()
                        .finderCode(code.generateFinderCode())
                        .nom(request.getNom())
                        .user(user)
                        .prenom(request.getPrenom())
                        .dateNaissance(request.getBirthDay())
                        .email(request.getUsername())
                        .telephone(request.getNumTel())
                        .sexe(request.getSexe())
                        .build()
        );

        localisationRepository.save(
                Localisation.builder()
                        .finder(finder)
                        .pays(request.getPays())
                        .ville(request.getVille())
                        .build()
        );

        return finder;

    }

    @Override
    public Finder getAllFinders() {
        return null;
    }

    @Override
    public FinderResponse getSpecificFinder(Integer finderId) {
        Localisation finderLocalisation = localisationRepository.findByFinderFinderId(finderId).orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable"));
        return FinderResponse.builder()
                .nom(finderLocalisation.getFinder().getNom())
                .prenom(finderLocalisation.getFinder().getPrenom())
                .username(finderLocalisation.getFinder().getEmail())
                .birthDay(finderLocalisation.getFinder().getDateNaissance())
                .sexe(finderLocalisation.getFinder().getSexe())
                .numTel(finderLocalisation.getFinder().getTelephone())
                .photoProfil(finderLocalisation.getFinder().getPhotoProfil())
                .pays(finderLocalisation.getPays())
                .ville(finderLocalisation.getVille())
                .photoProfil(finderLocalisation.getFinder().getPhotoProfil())
                .build();
    }

    @Override
    @Transactional
    public FinderResponse updateFinder(Integer finderId, UpdateFinderRequest request) {
        updateFinderValidator.validate(request);
        Localisation localisation = localisationRepository.findByFinderFinderId(finderId).orElseThrow(() -> new EntityNotFoundException("Données sur la localisation de l'utilisateur introuvable"));
        Finder finder = finderRepository.findByFinderId(finderId).orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable") );

        //Update Finder's localisation data
        localisation.setPays(request.getPays());
        localisation.setVille(request.getVille());

        //Update finder data
        finder.setNom(request.getNom());
        finder.setPrenom(request.getPrenom());
        finder.setSexe(request.getSexe());
        finder.setDateNaissance(request.getBirthDay());
        finder.setTelephone(request.getNumTel());
        finder.setPhotoProfil(request.getPhotoProfil());

        localisationRepository.save(localisation);
        finderRepository.save(finder);

        return FinderResponse.builder()
                .nom(finder.getNom())
                .prenom(finder.getPrenom())
                .username(finder.getUser().getUsername())
                .photoProfil(finder.getPhotoProfil())
                .numTel(finder.getTelephone())
                .sexe(finder.getSexe())
                .pays(localisation.getPays())
                .ville(localisation.getVille())
                .build();
    }

    @Override
    @Transactional
    public void deleteFinder(Integer finderId) {

    }
}
