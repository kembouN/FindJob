package com.jobfinder.entities.enterprise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobfinder.entities.BaseEntity;
import com.jobfinder.entities.user.UserJobFinder;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Enterprise extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enterpriseId;

    private String enterpriseCode;

    private String enterpriseName;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserJobFinder user;

    private String enterpriseEmail;

    private String description;

    private String siteLink;

    private String sector;

    private Integer telephone;

    private byte[] profil;
}
