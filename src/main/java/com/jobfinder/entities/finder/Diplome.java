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
public class Diplome extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer diplomeId;

    @Column(unique = true, nullable = false)
    private String libelle;

    @Column(unique = true, nullable = false)
    private String code;
}
