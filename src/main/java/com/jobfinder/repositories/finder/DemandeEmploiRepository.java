package com.jobfinder.repositories.finder;

import com.jobfinder.entities.job.DemandeEmploi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeEmploiRepository extends JpaRepository<DemandeEmploi, Integer> {
}
