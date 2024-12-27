package com.jobfinder.dto.job;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobRequest {

    @NotNull(message = "Utilisateur non renseigné")
    private Integer finderId;

    @NotEmpty(message = "Veuillez renseigner le thème du poste")
    private String jobTitle;

    private boolean isFullTime;

    @NotNull(message = "Quel est le nombre d'année minimum requis")
    private Integer experienceMin;

    private boolean isRemote;

    private Integer salary;

    @NotEmpty(message = "Entrez le secteur d'activité")
    private String domaine;

    @NotNull(message = "Entrez le type de contrat")
    private Integer typeContratId;

    @NotEmpty(message = "Entrez une description détaillée")
    private String description;

    private LocalDate delai;

    private String country;

    private String town;

    @NotNull
    private List<String> exigences;

    @NotNull
    private List<String> missions;

    private List<NiveauRequisDto> studyLevels;
}
