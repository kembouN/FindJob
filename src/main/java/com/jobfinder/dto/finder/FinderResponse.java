package com.jobfinder.dto.finder;

import com.jobfinder.entities.finder.SexEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinderResponse {

    private String nom;

    private String prenom;

    private String username;

    private String pays;

    private String ville;

    private LocalDate birthDay;

    private SexEnum sexe;

    private Integer numTel;

    private byte[] photoProfil;
}
