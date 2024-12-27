package com.jobfinder.services.finder.impl;

import com.jobfinder.dto.finder.DiplomeRequest;
import com.jobfinder.entities.finder.Diplome;
import com.jobfinder.exception.OperationNonPermittedException;
import com.jobfinder.repositories.finder.DiplomeRepository;
import com.jobfinder.services.finder.IDiplomeService;
import com.jobfinder.validator.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class DiplomeService implements IDiplomeService {

    @Autowired
    private ObjectValidator<DiplomeRequest> diplomeValidator;

    @Autowired
    private DiplomeRepository diplomeRepository;


    @Override
    public Diplome addDiplome(DiplomeRequest request) {
        diplomeValidator.validate(request);
        if (diplomeRepository.countAllByLibelleIgnoreCase(request.getLibelle().trim()) != 0){
            throw new OperationNonPermittedException("Opération interrompue, libellé du diplome répliqué");
        } else if (diplomeRepository.countAllByCodeIgnoreCase(request.getCode().trim()) != 0) {
            throw new OperationNonPermittedException("Opération interrompue, code du diplome répliqué");
        }
        return diplomeRepository.save(
            Diplome.builder()
                    .libelle(request.getLibelle().trim())
                    .code(request.getCode().trim())
                    .build()
        );
    }

    @Override
    public List<Diplome> getAllDiplome() {
        return diplomeRepository.findAll();
    }

    @Override
    public Void deleteDiplome(Integer diplomeId){
        Diplome diplome = diplomeRepository.findByDiplomeId(diplomeId);
        if (diplome == null)
            throw new EntityNotFoundException("Diplome introuvable");
        diplomeRepository.delete(diplome);
        return null;
    }
}
