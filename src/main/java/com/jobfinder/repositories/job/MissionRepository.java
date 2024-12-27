package com.jobfinder.repositories.job;

import com.jobfinder.entities.job.Job;
import com.jobfinder.entities.job.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Integer> {

    int countAllByJobAndDescriptionIgnoreCase(Job job, String description);
}
