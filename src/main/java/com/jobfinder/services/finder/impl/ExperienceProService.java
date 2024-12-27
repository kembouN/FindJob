package com.jobfinder.services.finder.impl;

import com.jobfinder.dto.finder.ExperienceProDto;
import com.jobfinder.dto.finder.ExperienceProRequest;
import com.jobfinder.entities.finder.ExperiencePro;
import com.jobfinder.entities.finder.Finder;
import com.jobfinder.exception.OperationNonPermittedException;
import com.jobfinder.repositories.finder.ExperienceProRepository;
import com.jobfinder.repositories.finder.FinderRepository;
import com.jobfinder.services.finder.IExperienceProService;
import com.jobfinder.validator.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceProService implements IExperienceProService {

    @Autowired
    private ExperienceProRepository experienceRepository;

    @Autowired
    private FinderRepository finderRepository;

    @Autowired
    private ObjectValidator<ExperienceProRequest> experienceValidator;


    @Override
    public List<ExperiencePro> addExperience(ExperienceProRequest request) {
        experienceValidator.validate(request);
        Finder finder = finderRepository.findByFinderId(request.getFinderId())
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable"));
        ArrayList<ExperiencePro> result = new ArrayList<>();
        for (ExperienceProDto experience: request.getExperiences()) {
            int rang = request.getExperiences().indexOf(experience)+1;
            if (experience.getStartYear().isAfter(experience.getEndYear())){
                throw new OperationNonPermittedException("Expérience n°"+rang+": La date de début ne peut être supérieure à la date de fin");
            }
            int replica = experienceRepository
                    .countAllByFinderFinderIdAndEntrepriseIgnoreCaseAndDateDebutGreaterThanEqualAndDateFinLessThanEqual(
                            request.getFinderId(), experience.getEnterprise(), experience.getStartYear(), experience.getEndYear()
                    );
            if (replica != 0){
                throw new OperationNonPermittedException("Cette expérience professionnelle existe déjà");
            }
            result.add(
                    experienceRepository.save(
                            ExperiencePro.builder()
                                    .dateDebut(experience.getStartYear())
                                    .dateFin(experience.getEndYear())
                                    .entreprise(experience.getEnterprise().trim())
                                    .poste(experience.getPoste().trim())
                                    .finder(finder)
                                    .build()
                    ));
        }
        return result;
    }

    @Override
    public List<ExperiencePro> getAllFinderExperience(Integer finderId) {
        return experienceRepository.findAllByFinderFinderId(finderId);
    }

    @Override
    public Void deleteExperience(List<Integer> experinceIds) {
        for (Integer experienceId : experinceIds) {
            int rang = experinceIds.indexOf(experienceId)+1;
            experienceRepository.findByExperienceId(experienceId).orElseThrow(() -> new EntityNotFoundException("Expérience n°"+ rang +"introuvable"));
        }
        experienceRepository.deleteAllById(experinceIds);
        return null;
    }
}
