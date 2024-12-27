package com.jobfinder.entities.finder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobfinder.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExperiencePro extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer experienceId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "finder_id")
    private Finder finder;

    private String entreprise;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private String poste;
}
