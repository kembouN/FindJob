package com.jobfinder.services.job;

import com.jobfinder.dto.job.JobRequest;
import com.jobfinder.dto.job.JobResponse;
import com.jobfinder.entities.job.Job;

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
}
