package com.jobfinder.dto.job;

import com.jobfinder.entities.job.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobResponse {

    private Integer jobId;

    private String jobTilte;

    private boolean isFullTime;

    private Integer experienceMin;

    private boolean isRemote;

    private  Integer salary;

    private String domaine;

    private TypeContrat typeContrat;

    private String jobDescription;

    private LocalDate delai;

    private Set<Exigence> exigences;

    private Set<Mission> missions;

    private Set<NiveauRequis> levels;

    private Set<Localisation> localisation;
}
