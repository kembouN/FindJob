package com.jobfinder.services.finder.impl;

import com.jobfinder.dto.finder.RegisterFinder;
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
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FinderService implements IFinderService {

    private FinderRepository finderRepository;

    private LocalisationRepository localisationRepository;

    private GenerateCodeUtils code;

    private IUserJobFinderDetailsService userService;

    private ObjectValidator<RegisterFinder> finderValidator;

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
    public Finder getSpecificFinder(Integer finderId) {
        return null;
    }

    @Override
    public Finder updateFinder(Integer finderId, RegisterFinder request) {
        return null;
    }

    @Override
    public void deleteFinder(Integer finderId) {

    }
}
