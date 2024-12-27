package com.jobfinder.repositories.job;

import com.jobfinder.entities.job.Exigence;
import com.jobfinder.entities.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExigenceRepository extends JpaRepository<Exigence, Integer> {

    int countAllByJobAndDescriptionIgnoreCase(Job job, String description);
}
