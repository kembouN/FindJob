package com.jobfinder.entities.job;

import com.jobfinder.entities.BaseEntity;
import com.jobfinder.entities.finder.Finder;
import com.jobfinder.entities.user.UserJobFinder;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Localisation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer localisationId;

    @ManyToOne
    @JoinColumn(name = "finder_id")
    private Finder finder;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private String pays;

    private String ville;
}
