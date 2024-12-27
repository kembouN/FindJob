package com.jobfinder.services.job;

import com.jobfinder.dto.job.LocalisationRequest;
import com.jobfinder.entities.job.Localisation;

public interface ILocalisationService {

    Localisation addLocalisation(LocalisationRequest request);
}
