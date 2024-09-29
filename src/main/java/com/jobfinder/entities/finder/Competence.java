package com.jobfinder.entities.finder;

import com.jobfinder.entities.BaseEntity;
import com.jobfinder.entities.user.UserJobFinder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Competence extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer competenceId;

    @ManyToOne
    @JoinColumn(name = "finder_id")
    private Finder finder;

    private String libelle;
}
