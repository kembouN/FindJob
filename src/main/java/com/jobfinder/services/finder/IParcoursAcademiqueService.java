package com.jobfinder.services.finder;

import com.jobfinder.dto.finder.ParcoursAcademiqueRequest;
import com.jobfinder.entities.finder.ParcoursAcademique;

import java.util.List;

public interface IParcoursAcademiqueService {

    List<ParcoursAcademique> getAllFinderParcoursAcademique(Integer finderId);

    List<ParcoursAcademique> addParcoursAcademique(ParcoursAcademiqueRequest request);

    Void deleteFinderParcours(Integer parcoursId);
}
