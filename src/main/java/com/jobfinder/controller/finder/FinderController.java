package com.jobfinder.controller.finder;

import com.jobfinder.dto.BaseResponse;
import com.jobfinder.dto.finder.*;
import com.jobfinder.entities.finder.Finder;
import com.jobfinder.services.finder.ICompetenceService;
import com.jobfinder.services.finder.IFinderService;
import com.jobfinder.services.finder.IParcoursAcademiqueService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/finder")
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

    @GetMapping("/{finderId}")
    @Operation(description = "Obtenir les détails sur un finder")
    public ResponseEntity<BaseResponse<FinderResponse>> getFinder(@PathVariable Integer finderId){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Opération réussie",
                        finderService.getSpecificFinder(finderId)
                )
        );
    }

    @PutMapping("/{finderId}")
    @Operation(description = "Mettre à jour les information d'un utilisateur")
    public ResponseEntity<BaseResponse<FinderResponse>> update(@PathVariable Integer finderId, @RequestBody UpdateFinderRequest request){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Informations mises à jour",
                        finderService.updateFinder(finderId, request)
                )
        );
    }

    @PutMapping("/{finderId}/add-picture")
    @Operation(description = "Ajouter une photo de profile utilisateur")
    public ResponseEntity<BaseResponse<Void>> addPicture(@PathVariable Integer finderId, @RequestBody MultipartFile photo){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Photo ajoutée",
                        finderService.addProfilePicture(finderId, photo)
                )
        );
    }

    @GetMapping("/{fniderId}/get-picture")
    @Operation(description = "Obtenir la photo de profil de l'utilisateur")
    public ResponseEntity<BaseResponse<byte[]>> getFinderPic(@PathVariable Integer finderId){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Photo de profile obtenue",
                        finderService.getFinderPicture(finderId)
                )
        );
    }

}
