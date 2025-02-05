package com.jobfinder.entities.finder;

import com.jobfinder.entities.BaseEntity;
import com.jobfinder.entities.job.Job;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Candidature extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer candidatureId;

    @OneToOne
    @JoinColumn(name = "job")
    private Job job;

    @OneToOne
    @JoinColumn(name = "finder")
    private Finder finder;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] curriculum;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] lettreMotivation;

    private String attachMessage;
}