package com.jobfinder.controller.job;

import com.jobfinder.dto.BaseResponse;
import com.jobfinder.dto.job.*;
import com.jobfinder.entities.job.Exigence;
import com.jobfinder.entities.job.Job;
import com.jobfinder.entities.job.Mission;
import com.jobfinder.entities.job.NiveauRequis;
import com.jobfinder.services.job.IJobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job")
@Tag(name = "Job")
public class JobController {

    @Autowired
    private IJobService jobService;

    @PostMapping
    @Operation(description = "Ajouter un nouveau job")
    public ResponseEntity<BaseResponse<Job>> newJob(@RequestBody JobRequest request){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Offre d'emploi créée",
                        jobService.addJob(request)
                )
        );
    }

    @GetMapping
    @Operation(description = "Obtenir la liste des offres d'emploi, selon différents critères de recherches")
    public ResponseEntity<BaseResponse<List<JobResponse>>> getAllJob(
            @RequestParam(required = false) String jobTitle,
            @RequestParam(required = false) Boolean isFullTime,
            @RequestParam(required = false) Boolean isRemote,
            @RequestParam(required = false) Integer salary,
            @RequestParam(required = false) String domaine,
            @RequestParam(required = false) String typeContrat,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String localisation
    ){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Opération réussie",
                        jobService.getJobByResearch(jobTitle, isFullTime, isRemote, salary, domaine, typeContrat, publisher, localisation)
                )
        );
    }

    @PostMapping("/missions")
    @Operation(description = "Ajouter des missions à un job")
    public ResponseEntity<BaseResponse<List<Mission>>> storeMissions(@RequestBody MissionRequest request){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Mission(s) ajoutée(s)",
                        jobService.addMision(request)
                )
        );
    }

    @PostMapping("/exigences")
    @Operation(description = "Ajouter des exigences à un job")
    public ResponseEntity<BaseResponse<List<Exigence>>> storeExigences(@RequestBody ExigenceRequest request){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Exigence(s) ajoutée(s)",
                        jobService.addExigence(request)
                )
        );
    }

    @PostMapping("/study-level")
    @Operation(description = "Ajouter des niveaux d'étude  un job")
    public ResponseEntity<BaseResponse<List<NiveauRequis>>> storeLevel(NiveauRequisRequest request){
        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        "Niveau(x) d'étude ajouté(s)",
                        jobService.addStudyLevel(request)
                )
        );
    }
}
