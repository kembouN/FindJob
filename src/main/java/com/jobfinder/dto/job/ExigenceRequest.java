package com.jobfinder.dto.job;

import com.jobfinder.entities.job.Job;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExigenceRequest {

    @NotNull(message = "Aucune exigence n'a été ajoutée pour ce job")
    private List<String> exigences;

    private Job job;

    private Integer jobId;
}
