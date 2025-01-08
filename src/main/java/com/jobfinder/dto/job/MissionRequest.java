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
public class MissionRequest {

    @NotNull(message = "Aucune mission n'a été renseignée")
    private List<String> missions;

    private Integer jobId;

    private Job job;
}
