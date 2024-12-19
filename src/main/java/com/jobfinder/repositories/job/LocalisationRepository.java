package com.jobfinder.repositories.job;

import com.jobfinder.entities.job.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalisationRepository extends JpaRepository<Localisation, Integer> {
    Optional<Localisation> findByFinderFinderId(Integer finderId);

    Optional<Localisation> findByLocalisationId(Integer localisationId);
}
