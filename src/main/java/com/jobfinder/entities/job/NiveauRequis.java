package com.jobfinder.entities.job;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobfinder.entities.BaseEntity;
import com.jobfinder.entities.finder.Diplome;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class NiveauRequis extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer niveauId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private String libelle;

    @ManyToOne
    @JoinColumn(name = "diplome_id")
    private Diplome diplome;
}
