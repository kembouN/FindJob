package com.jobfinder.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobfinder.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_account")
public class UserJobFinder extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    @Column(unique = true, nullable = false)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String pwd;

    private LocalDate lastConnection;

    private Integer activationCode;

    private LocalDate codeSentAt;

    @ColumnDefault("false")
    private boolean isActive;

    @ColumnDefault("0")
    private boolean isSuperAdmin;

    @ColumnDefault("0")
    private boolean isEnterprise;

}
