package com.jobfinder.services.finder.impl;

import com.jobfinder.dto.finder.ParcoursAcademiqueRequest;
import com.jobfinder.dto.finder.ParcoursAcademiqueResponse;
import com.jobfinder.dto.finder.ParcoursDto;
import com.jobfinder.entities.finder.Finder;
import com.jobfinder.entities.finder.ParcoursAcademique;
import com.jobfinder.exception.OperationNonPermittedException;
import com.jobfinder.repositories.finder.DiplomeRepository;
import com.jobfinder.repositories.finder.FinderRepository;
import com.jobfinder.repositories.finder.ParcoursAcademiqueRepository;
import com.jobfinder.services.finder.IParcoursAcademiqueService;
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
public class ParcoursAcademiqueService implements IParcoursAcademiqueService {

    @Autowired
    private ParcoursAcademiqueRepository parcoursAcademiqueRepository;

    @Autowired
    private ObjectValidator<ParcoursAcademiqueRequest> parcoursValidator;

    @Autowired
    private DiplomeRepository diplomeRepository;

    @Autowired
    private FinderRepository finderRepository;

    @Override
    public List<ParcoursAcademique> getAllFinderParcoursAcademique(Integer finderId) {
        return parcoursAcademiqueRepository.findAllByFinderFinderId(finderId);
    }

    @Override
    public List<ParcoursAcademique> addParcoursAcademique(ParcoursAcademiqueRequest request) {
        parcoursValidator.validate(request);
        Finder finder = finderRepository.findByFinderId(request.getFinderId()).orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable"));
        List<ParcoursAcademique> result = new ArrayList<>();
        for (ParcoursDto parcours: request.getParcours()) {
            int rang = request.getParcours().indexOf(parcours)+1;
            if (parcours.getStartDate() > parcours.getEndDate()){
                throw new OperationNonPermittedException("Parcours n°"+ rang +": La date de début ne peut être supérieure à la date de fin");
            }else if (isExistsParcours(parcours, finder) ){
                throw new OperationNonPermittedException("Parcours n°"+ rang +": un parcours académique similaire, existe déjà");
            }else {
                ParcoursAcademique saved = parcoursAcademiqueRepository.save(
                        ParcoursAcademique.builder()
                                .debutAnnee(parcours.getStartDate())
                                .etablissement(parcours.getEtablissement().trim())
                                .diplome(diplomeRepository.findByDiplomeId(parcours.getDiplomeId()))
                                .finAnnee(parcours.getEndDate())
                                .finder(finder)
                                .build()
                );
                result.add(saved);
            }

        }
        return result;
    }

    @Override
    public Void deleteFinderParcours(Integer parcoursId) {
        ParcoursAcademique parcours = parcoursAcademiqueRepository.findByParcoursId(parcoursId).orElseThrow(() -> new EntityNotFoundException("Parcours académique introuvable"));
        parcoursAcademiqueRepository.delete(parcours);
        return null;
    }

    public  boolean isExistsParcours(ParcoursDto parcours, Finder finder){
        List<ParcoursAcademique> parcours1 = parcoursAcademiqueRepository.findAllByFinderAndEtablissementIgnoreCaseAndAndDebutAnneeGreaterThanEqualAndFinAnneeLessThanEqual(
                finder,
                parcours.getEtablissement().trim(),
                parcours.getStartDate(),
                parcours.getEndDate()
        );
        boolean value = false;
        for (ParcoursAcademique parcours2 : parcours1) {
            if (parcours2 != null && parcours2.getDiplome().equals(diplomeRepository.findByDiplomeId(parcours.getDiplomeId()))){
                value = true;
            }
        }
        return value;
    }
}
