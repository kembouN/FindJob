package com.jobfinder.controller.job;

import com.jobfinder.dto.BaseResponse;
import com.jobfinder.dto.job.TypeContratRequest;
import com.jobfinder.entities.job.TypeContrat;
import com.jobfinder.services.job.ITypeContratService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/type-contrat")
@Tag(name = "Type contrat")
public class TypeContratController {

    @Autowired
    private ITypeContratService contratService;

    @PostMapping
    @Operation(description = "Ajouter un type de contrat")
    public ResponseEntity<BaseResponse<TypeContrat>> addContrat(@RequestBody TypeContratRequest request){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Type de contrat ajouté",
                        contratService.addTypeContrat(request)
                )
        );
    }

    @GetMapping
    @Operation(description = "Obtenir la liste de tous les tyes de contrat")
    public ResponseEntity<BaseResponse<List<TypeContrat>>> getAllTypeContrat(){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Opération réussie",
                        contratService.getAllTypeContrat()
                )
        );
    }

    @DeleteMapping("/typeContratId")
    @Operation(description = "Supprimer un type de contrat spécifique")
    public ResponseEntity<BaseResponse<Void>> deleteContrat(Integer typeContratId){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Type de contrat supprimé",
                        contratService.deleteTypeContrat(typeContratId)
                )
        );
    }
}
