package com.jobfinder.services.finder;

import com.jobfinder.dto.finder.DiplomeRequest;
import com.jobfinder.entities.finder.Diplome;

import java.util.List;

public interface IDiplomeService {

    Diplome addDiplome(DiplomeRequest request);

    List<Diplome> getAllDiplome();

    Void deleteDiplome(Integer diplomeId);
}
