package com.jobfinder.controller.finder;

import com.jobfinder.dto.BaseResponse;
import com.jobfinder.dto.finder.RegisterFinder;
import com.jobfinder.dto.user.UserDto;
import com.jobfinder.entities.finder.Finder;
import com.jobfinder.services.finder.IFinderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/finder")
public class FinderController {

    private IFinderService finderService;

    @PostMapping
    @Operation(description = "Enregistrer un utilisateur")
    public ResponseEntity<BaseResponse<Finder>> addFinder(@RequestBody RegisterFinder request){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Votre compte a été créé et un mail de confirmation vous sera envoyé",
                        finderService.createFinder(request)
                )
        );
    }
}
