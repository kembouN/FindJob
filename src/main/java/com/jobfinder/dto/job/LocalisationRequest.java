package com.jobfinder.dto.job;

import com.jobfinder.entities.finder.Finder;
import com.jobfinder.entities.job.Job;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocalisationRequest {

    private Job job;

    private Finder finder;

    @NotEmpty(message = "Veuillez renseigner la vile")
    private String town;

    @NotEmpty(message = "Veuillez renseignez le pays")
    private String country;
}
