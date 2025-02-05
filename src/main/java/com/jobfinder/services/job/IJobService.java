package com.jobfinder.services.job;

import com.jobfinder.dto.job.*;
import com.jobfinder.entities.job.Exigence;
import com.jobfinder.entities.job.Job;
import com.jobfinder.entities.job.Mission;
import com.jobfinder.entities.job.NiveauRequis;

import java.util.List;

public interface IJobService {

    Job addJob(JobRequest request);

    List<JobResponse> getJobByResearch(
            String jobTitle,
            Boolean isFullTime,
            Boolean isRemote,
            Integer salary,
            String domaine,
            String typeContrat,
            String publisher,
            String localisation
            );

    Void deleteJob(Integer jobId);

    List<Mission> addMision(MissionRequest request);

    List<Exigence> addExigence(ExigenceRequest request);

    List<NiveauRequis> addStudyLevel(NiveauRequisRequest request);

    List<JobResponse> getJobByFinderWithFilter(Integer finderId, String jobTitle, Boolean isFullTime, Boolean isRemote, Integer salary, String typeContrat, String localisation);
}
