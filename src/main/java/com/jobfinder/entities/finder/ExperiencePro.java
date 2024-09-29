package com.jobfinder.entities.finder;

import com.jobfinder.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperiencePro extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer experienceId;

    @ManyToOne
    @JoinColumn(name = "finder_id")
    private Finder finder;

    private String entreprise;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private String poste;
}
