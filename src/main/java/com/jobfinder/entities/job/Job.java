package com.jobfinder.entities.job;

import com.jobfinder.entities.BaseEntity;
import com.jobfinder.entities.enterprise.Enterprise;
import com.jobfinder.entities.finder.Finder;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;

    private String jobTitle;

    private boolean isFullTime;

    @OneToOne
    @JoinColumn(name = "type_contrat")
    private TypeContrat typeContrat;

    private Integer anneeExpMin;

    private boolean isRemote;

    private Integer salaire;

    private String domaine;

    private String description;

    @ColumnDefault(value = "0")
    private Integer totalCandidat;
/*
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isDeleted;
*/
    private LocalDate delai;

    @OneToOne
    @JoinColumn(name = "publisher")
    private Finder finder;

    @OneToOne
    @JoinColumn(name = "enterprise")
    private Enterprise enterprise;

    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
    private Set<Mission> missions;

    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
    private Set<Exigence> exigences;

    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
    private Set<NiveauRequis> niveauRequis;

    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
    private Set<Localisation> localisations;

}
