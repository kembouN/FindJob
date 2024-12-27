package com.jobfinder.repositories.finder;

import com.jobfinder.entities.finder.Diplome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiplomeRepository extends JpaRepository<Diplome, Integer> {

    Diplome findByDiplomeId(Integer diplomeId);

    int countAllByLibelleIgnoreCase(String libelle);

    int countAllByCodeIgnoreCase(String code);
}
