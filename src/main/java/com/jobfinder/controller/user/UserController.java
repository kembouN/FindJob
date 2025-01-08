package com.jobfinder.controller.user;

import com.jobfinder.dto.BaseResponse;
import com.jobfinder.dto.auth.LoginRequest;
import com.jobfinder.dto.auth.LoginResponse;
import com.jobfinder.dto.user.ChangePasswordRequest;
import com.jobfinder.dto.user.RegisterUserRequest;
import com.jobfinder.entities.user.UserJobFinder;
import com.jobfinder.services.user.IUserJobFinderDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private IUserJobFinderDetailsService userService;

    @PostMapping("/register")
    @Operation(description = "Création d'un compte utilisateur")
    public ResponseEntity<BaseResponse<UserJobFinder>> register(@RequestBody RegisterUserRequest request){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Compte créé",
                        userService.register(request)
                )
        );
    }

    @PostMapping("/login")
    @Operation(description = "Authentifier un utilisateur")
    public ResponseEntity<BaseResponse<LoginResponse>> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Authentification réussie",
                        userService.login(request)
                )
        );
    }

    @PutMapping("/activate-account/{accountId}")
    @Operation(description = "Activation du compte utilisateur avec un code d'activation")
    public ResponseEntity<BaseResponse<Void>> activate(@PathVariable Integer accountId, @RequestParam Integer activationCode){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Activation réussie",
                        userService.activateAccount(accountId, activationCode)
                )
        );
    }

    @PostMapping("/reset-password")
    @Operation(description = "Réinitialisation du mot de passe utilisateur en cas d'oublie")
    public ResponseEntity<BaseResponse<Void>> reset(@RequestParam(name = "email") @Email(message = "Votre adresse email est incorrecte") String email){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Mot de passe réinitialisé avec succès",
                        userService.resetPassword(email)
                )
        );
    }

    @PutMapping("/change-account-password/{accountId}")
    @Operation(description = "Modification du mot de passe utilisateur")
    public ResponseEntity<BaseResponse<Void>> changePassword(@PathVariable Integer accountId, @RequestBody ChangePasswordRequest request){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Mot de passe modifié avec succès",
                        userService.changePassword(accountId, request)
                )
        );
    }

    @PutMapping("/deactivate-account/{accountEmail}")
    @Operation(description = "Désactiver un compte utilisateur à partir de son email")
    public ResponseEntity<BaseResponse<Void>> deactivate(@PathVariable String accountEmail){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Compte utilisateur désactivé",
                        userService.deactivateAccountWithEmail(accountEmail)
                )
        );
    }

}
