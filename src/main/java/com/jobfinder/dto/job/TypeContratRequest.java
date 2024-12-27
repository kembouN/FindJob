package com.jobfinder.dto.job;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeContratRequest {

    @NotEmpty(message = "Veuillez renseigner le libellé du type de contrat")
    private String libelle;
}
