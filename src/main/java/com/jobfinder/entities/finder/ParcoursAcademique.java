package com.jobfinder.entities.finder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobfinder.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParcoursAcademique extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer parcoursId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "finder_id")
    private Finder finder;

    private Integer debutAnnee;

    private Integer finAnnee;

    private String etablissement;

    @ManyToOne
    @JoinColumn(name = "diplome_id")
    private Diplome diplome;
}
