package com.jobfinder.entities;

import com.jobfinder.entities.user.UserJobFinder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Finder extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @OneToOne
    @JoinColumn(name = "account_id")
    private UserJobFinder account;

    private String userCode;

    private String nom;

    private String prenom;

    private String email;

    private LocalDate dateNaissance;

    private String sexe;

    private Integer telephone;
}
