package com.jobfinder.controller.finder;

import com.jobfinder.dto.BaseResponse;
import com.jobfinder.dto.finder.CompetenceListResponse;
import com.jobfinder.dto.finder.CompetenceRequest;
import com.jobfinder.entities.finder.Competence;
import com.jobfinder.services.finder.ICompetenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Profil")
@RequestMapping("/api/v1/profil")
public class CompetenceController {

    @Autowired
    private ICompetenceService competenceService;

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

    @GetMapping("/finder/{finderId}/get-competence")
    @Operation(description = "Afficher les compétences d'un utilisateur")
    public ResponseEntity<BaseResponse<CompetenceListResponse>> getFinderAllCompetence(Integer finderId){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Opération réussie",
                        competenceService.getAllFinderCompetences(finderId)
                )
        );
    }

    @DeleteMapping("/delete-competences")
    @Operation(description = "Supprimer une liste de compétences")
    public ResponseEntity<BaseResponse<Void>> deleteCompetences(@RequestParam List<Integer> competencesIds){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Competence(s) supprimée(s)",
                        competenceService.deleteCompetence(competencesIds)
                )
        );
    }

}
