package com.jobfinder.entities.user;

import com.jobfinder.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

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

    @Column(unique = true)
    private String username;

    private String pwd;

    private LocalDate lastConnection;

    private String activationCode;

    private LocalDate codeSentAt;

    @ColumnDefault("false")
    private boolean isActive;

    @ColumnDefault("0")
    private boolean isSuperAdmin;

    @ColumnDefault("0")
    private boolean isEnterprise;

}
