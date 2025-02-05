package com.jobfinder.repositories.finder;

import com.jobfinder.entities.finder.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatureRepository extends JpaRepository<Candidature, Integer> {

    Candidature findAllByFinderFinderIdOrderByCreatedAtDesc(Integer finderId);
}
