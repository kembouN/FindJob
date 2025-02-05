package com.jobfinder.dto.job;

import com.jobfinder.dto.finder.FinderResponse;
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

    private Integer totalCandidat;

    private String recruiter;

    private byte[] recruiterImage;

    private TypeContrat typeContrat;

    private String jobDescription;

    private LocalDate delai;

    private LocalDate uploadDate;

    private Set<Exigence> exigences;

    private Set<Mission> missions;

    private Set<NiveauRequis> levels;

    private Set<Localisation> localisation;
}
