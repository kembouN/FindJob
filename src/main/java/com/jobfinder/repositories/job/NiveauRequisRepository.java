package com.jobfinder.repositories.job;

import com.jobfinder.entities.job.Job;
import com.jobfinder.entities.job.NiveauRequis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NiveauRequisRepository extends JpaRepository<NiveauRequis, Integer> {

    int countAllByJobAndLibelleIgnoreCase(Job job, String libelle);
}
