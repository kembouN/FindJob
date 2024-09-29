package com.jobfinder.services.utils;

import com.jobfinder.entities.user.UserJobFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
@AllArgsConstructor
public class GenerateCodeUtils {

    public Integer generateActivationCode(){return new Random().nextInt(100000, 999999);}

    /*
    public Integer generateUserStrongPasswordReset(UserJobFinder user){


    }

     */
    public String generateFinderCode(){
        Integer year = LocalDate.now().getYear();
        Integer month = LocalDate.now().getMonthValue();
        Integer day = LocalDate.now().getDayOfMonth();
        return "FD"+""+year+""+month+""+day;
    }

}
