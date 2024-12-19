package com.jobfinder.entities.finder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobfinder.entities.BaseEntity;
import com.jobfinder.entities.user.UserJobFinder;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Repository;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Competence extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer competenceId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "finder_id")
    private Finder finder;

    private String libelle;
}
