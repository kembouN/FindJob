package com.jobfinder.repositories.job;

import com.jobfinder.entities.job.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalisationRepository extends JpaRepository<Localisation, Integer> {
}
