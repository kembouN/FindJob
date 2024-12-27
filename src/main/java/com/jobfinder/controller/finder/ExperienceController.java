package com.jobfinder.controller.finder;

import com.jobfinder.dto.BaseResponse;
import com.jobfinder.dto.finder.ExperienceProDto;
import com.jobfinder.dto.finder.ExperienceProRequest;
import com.jobfinder.entities.finder.ExperiencePro;
import com.jobfinder.services.finder.IExperienceProService;
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
public class ExperienceController {

    @Autowired
    private IExperienceProService experienceService;

    @PostMapping("/add-experience")
    @Operation(description = "Ajouter une ou plusieurs expériences professionnelles")
    public ResponseEntity<BaseResponse<List<ExperiencePro>>> addExperiencePro(@RequestBody ExperienceProRequest request){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Expérience(s) ajoutée(s)",
                        experienceService.addExperience(request)
                )
        );
    }

    @GetMapping("/finder/{finderId}/get-all-experience")
    @Operation(description = "Obtenir la liste des expériences professionnelles de l'utilisateur")
    public ResponseEntity<BaseResponse<List<ExperiencePro>>> getAllExperience(@PathVariable Integer finderId){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Opération réussie",
                        experienceService.getAllFinderExperience(finderId)
                )
        );
    }

    @DeleteMapping("/delete-experience")
    @Operation(description = "Supprimer un ou plusieurs expériences professionelles")
    public ResponseEntity<BaseResponse<Void>> deleteExperience(@RequestParam List<Integer> experienceIds){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Expérience(s) supprimée(s)",
                        experienceService.deleteExperience(experienceIds)
                )
        );
    }
}
