package com.jobfinder.dto.finder;

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
public class ExperienceProRequest {

    @NotNull(message = "Utilisateur non identifié")
    private Integer finderId;

    private List<ExperienceProDto> experiences;
}
