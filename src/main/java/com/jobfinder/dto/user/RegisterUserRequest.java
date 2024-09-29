package com.jobfinder.dto.user;

import jakarta.validation.constraints.Email;
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
public class RegisterUserRequest {

    @NotEmpty(message = "Veuillez entrer votre adresse mail")
    @Email(message = "Votre adresse email est incorrect")
    private String username;

    @NotEmpty(message = "Veuillez entrer un mot de passe sécurisé")
    @Size(min = 4, max = 20, message = "Le mot de passe doit compter entre quatre(4) et vingt(20) caractères")
    private String password;

    @NotEmpty(message = "Veuillez entrer une confirmation du mot de passe")
    private String cPassword;

    private Boolean isSuperAdmin;

    @NotNull(message = "Êtes-vous une entreprise?")
    private boolean isEnterprise;

}
