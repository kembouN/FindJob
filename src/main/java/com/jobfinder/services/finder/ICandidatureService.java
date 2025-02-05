package com.jobfinder.services.finder;

import com.jobfinder.dto.finder.ApplyRequest;
import com.jobfinder.dto.finder.CandidatureResponse;

public interface ICandidatureService {

    CandidatureResponse apply(ApplyRequest request);

    CandidatureResponse getAllFinderCandidature(Integer finderId);

    CandidatureResponse getAllCandidatureByJob(Integer jobId);
}
