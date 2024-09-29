package com.jobfinder.controller.user;

import com.jobfinder.dto.BaseResponse;
import com.jobfinder.dto.auth.LoginRequest;
import com.jobfinder.dto.auth.LoginResponse;
import com.jobfinder.dto.finder.RegisterFinder;
import com.jobfinder.dto.user.RegisterUserRequest;
import com.jobfinder.entities.user.UserJobFinder;
import com.jobfinder.services.user.IUserJobFinderDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
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
}
