package com.jobfinder.services.utils;

import com.jobfinder.entities.user.UserJobFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Random;

@Service
@AllArgsConstructor
public class GenerateCodeUtils {

    public Integer generateActivationCode(){return new Random().nextInt(100000, 999999);}

    public String generateUserStrongPasswordReset(UserJobFinder user){
        String[] userPseudo = user.getUsername().split("[.@]+");
        Integer year = user.getCreatedAt().getYear();
        Integer month = user.getCreatedAt().getMonthValue();
        String generatedPwd = "!"+""+userPseudo+""+year+""+generateActivationCode();
        return generatedPwd;
    }

    public String generateFinderCode(){
        Integer year = LocalDate.now().getYear();
        Integer month = LocalDate.now().getMonthValue();
        Integer day = LocalDate.now().getDayOfMonth();
        Instant time = Instant.now();
        return "FD"+""+year+""+month+""+day+""+time;
    }

}
