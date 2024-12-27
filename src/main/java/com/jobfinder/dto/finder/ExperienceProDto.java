package com.jobfinder.dto.finder;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExperienceProDto {

    @NotNull(message = "Veuillez renseigner une année de début")

    private LocalDate startYear;

    @NotNull(message = "Veuuillez renseigner une année de fin")
    private LocalDate endYear;

    @NotEmpty(message = "Veuillez renseigner le label de l'entreprise")
    private String enterprise;

    @NotEmpty(message = "Veuillez renseigner le poste occupé")
    private String poste;
}
