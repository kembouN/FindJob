package com.jobfinder.controller.finder;

import com.jobfinder.dto.BaseResponse;
import com.jobfinder.dto.finder.ParcoursAcademiqueRequest;
import com.jobfinder.dto.finder.ParcoursAcademiqueResponse;
import com.jobfinder.entities.finder.ParcoursAcademique;
import com.jobfinder.services.finder.IParcoursAcademiqueService;
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
public class ParcoursAcademiqueController {

    @Autowired
    private IParcoursAcademiqueService parcourService;


    @PostMapping("/add-parcours")
    @Operation(description = "Ajouter un parcours academique")
    public ResponseEntity<BaseResponse<List<ParcoursAcademique>>> addParcours(@RequestBody ParcoursAcademiqueRequest request){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Parcours ajouté(s)",
                        parcourService.addParcoursAcademique(request)
                )
        );
    }

    @GetMapping("/finder/{finderId}/get-parcours")
    @Operation(description = "Obtenir la liste des parcours académique d'un finder")
    public ResponseEntity<BaseResponse<List<ParcoursAcademique>>> getAllFinderParcours(@PathVariable Integer finderId){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Opération réussie",
                        parcourService.getAllFinderParcoursAcademique(finderId)
                )
        );
    }

    @DeleteMapping("/delete-parcours/{parcoursId}")
    @Operation(description = "Supprimer un parcours académique")
    public ResponseEntity<BaseResponse<Void>> deleteParcours(@PathVariable Integer parcoursId){
        return  ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Parcours académique supprimé",
                        parcourService.deleteFinderParcours(parcoursId)
                )
        );
    }

}
