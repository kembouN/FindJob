package com.jobfinder.entities.job;

import com.jobfinder.entities.BaseEntity;
import com.jobfinder.entities.user.UserJobFinder;
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
public class Localisation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer localisationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserJobFinder finder;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private String pays;

    private String ville;
}
