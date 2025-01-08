package com.jobfinder.services.job.impl;


import com.jobfinder.dto.job.TypeContratRequest;
import com.jobfinder.entities.job.TypeContrat;
import com.jobfinder.exception.OperationNonPermittedException;
import com.jobfinder.services.job.ITypeContratService;
import com.jobfinder.repositories.job.TypeContratRepository;
import com.jobfinder.validator.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeContratService implements ITypeContratService {

    @Autowired
    private ObjectValidator<TypeContratRequest> contratValidator;

    @Autowired
    private TypeContratRepository contratRepository;


    @Override
    public TypeContrat addTypeContrat(TypeContratRequest request) {
        contratValidator.validate(request);
        if (contratRepository.countAllByLibelleIgnoreCase(request.getLibelle().trim()) != 0)
            throw new OperationNonPermittedException("Ce type de contrat existe déjà");

        return contratRepository.save(
                TypeContrat.builder()
                        .libelle(request.getLibelle().trim())
                        .build()
        );
    }

    @Override
    public List<TypeContrat> getAllTypeContrat() {
        return contratRepository.findAll();
    }

    @Override
    public Void deleteTypeContrat(Integer typeContratId) {
        contratRepository.findById(typeContratId).orElseThrow(() -> new EntityNotFoundException("Le type de contrat sélectionné est introuvable"));
        contratRepository.deleteById(typeContratId);
        return null;
    }
}
