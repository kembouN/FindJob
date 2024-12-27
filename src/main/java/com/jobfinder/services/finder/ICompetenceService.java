package com.jobfinder.services.finder;

import com.jobfinder.dto.finder.CompetenceListResponse;
import com.jobfinder.dto.finder.CompetenceRequest;
import com.jobfinder.entities.finder.Competence;

import java.util.List;

public interface ICompetenceService {

    List<Competence> addCompetence(CompetenceRequest libelles);

    CompetenceListResponse getAllFinderCompetences(Integer finderId);

    Void deleteCompetence(List<Integer> competenceIds);
}
