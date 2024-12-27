package com.jobfinder.dto.finder;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiplomeRequest {

    @NotEmpty(message = "Veuillez renseigner le libelle du diplome")
    private String libelle;

    @NotEmpty(message = "Veuillez renseigner un code")
    private String code;

}
