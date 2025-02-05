package com.jobfinder.dto.finder;

import com.jobfinder.entities.finder.SexEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateFinderRequest {

    @NotEmpty(message = "Veuillez entrer votre nom")
    private String nom;

    private String prenom;

    @NotEmpty(message = "Veuillez entrer votre pays")
    private String pays;

    @NotEmpty(message = "Veuillez entrer votre ville")
    private String ville;

    @NotNull(message = "Veuillez renseigner votre date de naissance")
    private LocalDate birthDay;

    @NotNull(message = "Veuillez renseigner votre sexe")
    private SexEnum sexe;

    @NotNull(message = "Renseignez un numéro de téléphone")
    private Integer numTel;

    private String sector;

    private String activity;

}
