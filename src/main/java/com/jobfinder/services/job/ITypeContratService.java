package com.jobfinder.services.job;

import com.jobfinder.dto.job.TypeContratRequest;
import com.jobfinder.entities.job.TypeContrat;

import java.util.List;

public interface ITypeContratService {

    TypeContrat addTypeContrat(TypeContratRequest request);

    List<TypeContrat> getAllTypeContrat();

    Void deleteTypeContrat(Integer typeContratId);
}
