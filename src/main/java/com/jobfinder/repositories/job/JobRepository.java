package com.jobfinder.repositories.job;

import com.jobfinder.entities.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
}
