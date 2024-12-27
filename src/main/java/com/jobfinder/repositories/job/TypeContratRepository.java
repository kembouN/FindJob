package com.jobfinder.repositories.job;

import com.jobfinder.entities.job.TypeContrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeContratRepository extends JpaRepository<TypeContrat, Integer> {

    int countAllByLibelleIgnoreCase(String libelle);
}
