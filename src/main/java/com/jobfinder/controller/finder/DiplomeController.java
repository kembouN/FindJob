package com.jobfinder.controller.finder;

import com.jobfinder.dto.BaseResponse;
import com.jobfinder.dto.finder.DiplomeRequest;
import com.jobfinder.entities.finder.Diplome;
import com.jobfinder.services.finder.IDiplomeService;
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
@RequestMapping("/api/v1/diplome")
@Tag(name = "Diplome")
public class DiplomeController {

    @Autowired
    private IDiplomeService diplomeService;

    @PostMapping
    @Operation(description = "Ajouter un nouveau diplome")
    public ResponseEntity<BaseResponse<Diplome>> addDiplome(@RequestBody DiplomeRequest request){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Diplome ajouté",
                        diplomeService.addDiplome(request)
                )
        );
    }

    @GetMapping
    @Operation(description = "Obtenir tous les diplomes")
    public ResponseEntity<BaseResponse<List<Diplome>>> getAllDiplome(){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Opération réussie",
                        diplomeService.getAllDiplome()
                )
        );
    }

    @DeleteMapping("/{diplomeId}")
    @Operation(description = "Supprimer un diplome")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable Integer diplomeId){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Diplome supprimé",
                        diplomeService.deleteDiplome(diplomeId)
                )
        );
    }
}
