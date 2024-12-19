package com.jobfinder.services.user;

import com.jobfinder.dto.auth.LoginResponse;
import com.jobfinder.dto.auth.LoginRequest;
import com.jobfinder.dto.user.ChangePasswordRequest;
import com.jobfinder.dto.user.RegisterUserRequest;
import com.jobfinder.dto.user.UserDto;
import com.jobfinder.entities.user.UserJobFinder;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserJobFinderDetailsService {

    LoginResponse login(LoginRequest request);

    UserJobFinder register(RegisterUserRequest resquest);

    Void resetPassword(String email);

    Void changePassword(Integer accountId, ChangePasswordRequest request);

    Void activateAccount(Integer userId, Integer activationCode);

    Void deactivateAccountWithId(Integer accountId);

    Void deactivateAccountWithEmail(String email);

}
