package com.jobfinder.entities.finder;

import com.jobfinder.entities.BaseEntity;
import com.jobfinder.entities.user.UserJobFinder;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Finder extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer finderId;

    @OneToOne
    @JoinColumn(name = "account_id")
    private UserJobFinder user;

    private String finderCode;

    private String nom;

    private String prenom;

    private String email;

    private LocalDate dateNaissance;

    private String sexe;

    private Integer telephone;
}
