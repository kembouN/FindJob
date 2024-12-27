package com.jobfinder.dto.finder;

import com.jobfinder.entities.finder.Diplome;
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
public class ParcoursDto {

    @NotNull(message = "Veuillez renseigner une année de début")
    private Integer startDate;

    @NotNull(message = "Veuillez renseigner une date de fin")

    private Integer endDate;

    @NotEmpty(message = "Veuillez renseigner l'établissement")
    private String etablissement;

    @NotNull(message = "Veuillez renseigner le diplome")
    private Integer diplomeId;

}
