package com.jobfinder.services.job.impl;

import com.jobfinder.dto.job.LocalisationRequest;
import com.jobfinder.entities.job.Localisation;
import com.jobfinder.exception.OperationNonPermittedException;
import com.jobfinder.repositories.job.LocalisationRepository;
import com.jobfinder.services.job.ILocalisationService;
import com.jobfinder.validator.ObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalisationService implements ILocalisationService {

    @Autowired
    private LocalisationRepository localisationRepository;

    @Autowired
    private ObjectValidator<LocalisationRequest> localisationValidator;

    @Override
    public Localisation addLocalisation(LocalisationRequest request) {
        localisationValidator.validate(request);

        Localisation localisation = new Localisation();
        if (request.getJob() != null && request.getFinder() == null){
            if (localisationRepository.countAllByJobAndPaysIgnoreCaseAndVilleIgnoreCase(request.getJob(), request.getCountry(), request.getTown()) != 0){
                throw new OperationNonPermittedException("Cette localisation existe déjà pour ce même job");
            }
            localisation = localisationRepository
                    .save(
                            Localisation.builder()
                                    .ville(request.getTown())
                                    .pays(request.getCountry())
                                    .job(request.getJob())
                                    .build()
                    );
        } else if (request.getJob() == null && request.getFinder() != null) {
            if (localisationRepository.countAllByFinderAndPaysIgnoreCaseAndVilleIgnoreCase(request.getFinder(), request.getCountry(), request.getTown()) != 0){
                throw new OperationNonPermittedException("Cette localisation existe déjà pour cet utilisateur");
            }

            localisation = localisationRepository.save(
                    Localisation.builder()
                            .ville(request.getTown())
                            .pays(request.getCountry())
                            .finder(request.getFinder())
                            .build()
            );
        }

        return localisation;
    }
}
