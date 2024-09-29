package com.jobfinder.entities.job;

import com.jobfinder.entities.BaseEntity;
import com.jobfinder.entities.finder.Finder;
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
public class Record extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;

    @ManyToOne
    @JoinColumn(name = "finder_id")
    private Finder finder;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private String code;
}
