package com.jobfinder.services.finder.impl;

import com.jobfinder.dto.finder.CompetenceListResponse;
import com.jobfinder.dto.finder.CompetenceRequest;
import com.jobfinder.dto.finder.FinderResponse;
import com.jobfinder.entities.finder.Competence;
import com.jobfinder.entities.finder.Finder;
import com.jobfinder.entities.job.Localisation;
import com.jobfinder.repositories.finder.CompetenceRepository;
import com.jobfinder.repositories.finder.FinderRepository;
import com.jobfinder.repositories.job.LocalisationRepository;
import com.jobfinder.services.finder.ICompetenceService;
import com.jobfinder.validator.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CompetenceService implements ICompetenceService {

    private ObjectValidator<CompetenceRequest> competenceValidator;

    private FinderRepository finderRepository;

    private CompetenceRepository competenceRepository;

    private LocalisationRepository localisationRepository;


    @Override
    public List<Competence> addCompetence(CompetenceRequest request) {
        competenceValidator.validate(request);
        Finder finder = finderRepository.findByFinderId(request.getFinderId()).orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable"));
        for (String libelle:request.getLibelles() ) {
            if (competenceRepository.countAllByLibelleIgnoreCase(libelle.trim()) == 0){
                competenceRepository.save(Competence.builder()
                                .finder(finder)
                                .libelle(libelle.trim())
                        .build());
            }
        }
       return competenceRepository.findAllByFinderFinderId(request.getFinderId());

    }

    @Override
    public CompetenceListResponse getAllFinderCompetences(Integer finderId) {
        Localisation finderLocalisation = localisationRepository.findByFinderFinderId(finderId).orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable"));
        List<Competence> competences = competenceRepository.findAllByFinderFinderId(finderId);

        return CompetenceListResponse.builder()
                .finder(FinderResponse.builder()
                        .nom(finderLocalisation.getFinder().getNom())
                        .sexe(finderLocalisation.getFinder().getSexe())
                        .pays(finderLocalisation.getPays())
                        .birthDay(finderLocalisation.getFinder().getDateNaissance())
                        .numTel(finderLocalisation.getFinder().getTelephone())
                        .ville(finderLocalisation.getVille())
                        .prenom(finderLocalisation.getFinder().getPrenom())
                        .username(finderLocalisation.getFinder().getEmail())
                        .build())
                .competences(competences)
                .build();
    }
}
