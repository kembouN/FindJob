package com.jobfinder.dto.job;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NiveauRequisDto {

    @NotEmpty(message = "Renseignez une description du niveau requis")
    private String description;

    @NotNull(message = "Veuillez choisir le diplome")
    private Integer diplomeId;
}
