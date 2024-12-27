package com.jobfinder.services.finder;

import com.jobfinder.dto.finder.ExperienceProRequest;
import com.jobfinder.entities.finder.ExperiencePro;

import java.util.List;

public interface IExperienceProService {

    List<ExperiencePro> addExperience(ExperienceProRequest request);

    List<ExperiencePro> getAllFinderExperience(Integer finderId);

    Void deleteExperience(List<Integer> experinceIds);
}
