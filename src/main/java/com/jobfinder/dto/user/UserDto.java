package com.jobfinder.dto.user;

import com.jobfinder.entities.finder.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String username;

    private LocalDate derniereConnection;

    private boolean isActive;

    private boolean isEnterprise;

    private String codeUtilisateur;

    private String nom;

    private String prenom;

    private String email;

    private LocalDate dateNaissance;

    private SexEnum sexe;

    private Integer telephone;

}
