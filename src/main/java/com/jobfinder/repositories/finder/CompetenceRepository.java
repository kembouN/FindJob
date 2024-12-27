package com.jobfinder.repositories.finder;

import com.jobfinder.entities.finder.Competence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompetenceRepository extends JpaRepository<Competence, Integer> {

    Optional<Competence> findByCompetenceId(Integer competenceId);

    List<Competence> findAllByFinderFinderId(Integer finderId);

    Competence findDistinctByCompetenceId(Integer competenceId);

    int countAllByLibelleIgnoreCaseAndFinderFinderId(String libelle, Integer finderId);

}
