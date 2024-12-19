package com.jobfinder.dto.user;

import com.jobfinder.exception.OperationNonPermittedException;
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
public class ChangePasswordRequest {

    @NotEmpty(message = "Tous les champs du formulaire sont obligatoires")
    private String lastPassword;

    @NotEmpty(message = "Tous les champs du formulaire sont obligatoires")
    @Size(min = 4, max = 20, message = "Le nouveau mot de passe doit contenir entre quatre(4) et vingt(20) caractères")
    private String newPassword;

    @NotEmpty(message = "Tous les champs du formulaire sont obligatoires")
    @Size(min = 4, max = 20, message = "Le nouveau mot de passe doit contenir entre quatre(4) et vingt(20) caractères")
    private String cNewPassword;

    public static void checkPasswordConfimation(String pwd, String pwdC){
        if (!pwd.equals(pwdC)){
            throw new OperationNonPermittedException("La confirmation du mot de passe est incorrecte");
        }
    }

}
