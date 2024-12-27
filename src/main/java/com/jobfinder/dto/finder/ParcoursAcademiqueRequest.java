package com.jobfinder.dto.finder;

import com.jobfinder.entities.finder.Diplome;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParcoursAcademiqueRequest {

    @NotNull(message = "L'identifiant de l'utilisateur est obligatoire")
    private Integer finderId;

    private List<ParcoursDto> parcours;
}
