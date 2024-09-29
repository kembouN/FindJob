package com.jobfinder.entities.finder;

import com.jobfinder.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParcoursAcademique extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer parcoursId;

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
