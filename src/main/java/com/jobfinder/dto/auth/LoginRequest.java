package com.jobfinder.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @NotEmpty(message = "Veuillez renseigner votre adresse e-mail")
    @Email(message = "Votre adresse email ne correspond pas")
    private String username;

    @NotEmpty(message = "Veuillez renseigner votre mot de passe")
    private String password;

}
