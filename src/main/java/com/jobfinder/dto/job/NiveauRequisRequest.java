package com.jobfinder.dto.job;

import com.jobfinder.entities.job.Job;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NiveauRequisRequest {
    private List<NiveauRequisDto> studyLevel;

    private Job job;

    private Integer jobId;

}
