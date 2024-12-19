package com.jobfinder.controller.finder;

import com.jobfinder.dto.BaseResponse;
import com.jobfinder.dto.finder.*;
import com.jobfinder.dto.user.UserDto;
import com.jobfinder.entities.finder.Competence;
import com.jobfinder.entities.finder.Finder;
import com.jobfinder.services.finder.ICompetenceService;
import com.jobfinder.services.finder.IFinderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/finder")
public class FinderController {

    private IFinderService finderService;

    private ICompetenceService competenceService;

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

    @PostMapping("/add-competence")
    @Operation(description = "Ajouter des compétences à l'utilisateur")
    public ResponseEntity<BaseResponse<List<Competence>>> addCompetence(@RequestBody CompetenceRequest request){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Statut des compétences mis à jour",
                        competenceService.addCompetence(request)
                )
        );
    }

    @GetMapping("/{finderId}/get-competence")
    @Operation(description = "Afficher les compétences d'un utilisateur")
    public ResponseEntity<BaseResponse<CompetenceListResponse>> getFinderAllCompetence(Integer finderId){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "",
                        competenceService.getAllFinderCompetences(finderId)
                )
        );
    }
}
