package com.jobfinder.services.utils;

import com.jobfinder.entities.user.UserJobFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;

@Service
@AllArgsConstructor
public class GenerateCodeUtils {

    public Integer generateActivationCode(){return new Random().nextInt(100000, 999999);}

    public String generateUserStrongPasswordReset(UserJobFinder user){
        String[] userPseudo = user.getUsername().split("[.@]+");
        int year = user.getCreatedAt().getYear();
        Integer month = user.getCreatedAt().getMonthValue();
        return "!"+""+ Arrays.toString(userPseudo) +""+year+""+generateActivationCode();
    }

    public String generateFinderCode(){
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();
        return "FD"+""+year+""+month+""+day;
    }

    public String generateEnterpriseCode(){
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();
        return "ET"+""+year+""+month+""+day;

    }

}
