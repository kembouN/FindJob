package com.jobfinder.dto.finder;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterFinder {
    @NotEmpty(message = "Veuillez entrer votre nom")
    private String nom;

    private String prenom;

    @NotEmpty(message = "Veuillez entrer votre adresse mail")
    private String username;

    @NotEmpty(message = "Veuillez entrer un mot de passe sécurisé")
    @Size(min = 4, max = 20, message = "Le mot de passe doit compter entre quatre(4) et vingt(20) caractères")
    private String password;

    @NotEmpty(message = "Veuillez entrer une confirmation du mot de passe")
    private String cPassword;

    @NotNull(message = "Vous devez sélectionner un type de compte")
    private boolean isEnterprise;

    @NotEmpty(message = "Veuillez entrer votre pays")
    private String pays;

    @NotEmpty(message = "Veuillez entrer votre ville")
    private String ville;

    @NotNull(message = "Veuillez renseigner votre date de naissance")
    private LocalDate birthDay;

    @NotEmpty(message = "Veuillez renseigner votre sexe")
    private String sexe;

    @NotNull(message = "Renseignez un numéro de téléphone")
    private Integer numTel;

    private String photoProfil;

}
