package com.jobfinder.dto.finder;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplyRequest {

    @NotNull(message = "L'utilisateur n'est pas renseigné")
    private Integer finderId;

    @NotNull(message = "Le job n'est pas renseigné")
    private Integer jobId;

    @NotNull(message = "Veuillez fournir un curriculum")
    private byte cv;

    private byte lm;

    private String message;
}
