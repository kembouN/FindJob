package com.jobfinder.services.user.impl;

import com.jobfinder.entities.user.UserJobFinder;
import com.jobfinder.repositories.user.UserJobFinderRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserJobFinderDetailsService implements UserDetailsService {

    private UserJobFinderRepository userJobFinderRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserJobFinder jobFinder = userJobFinderRepository.findUserJobFinderByUsername(username);

        if (jobFinder != null) {
            UserDetails userDetails = User.withUsername(jobFinder.getUsername())
                    .password(jobFinder.getPwd())
                    .build();
            return userDetails;
        }
        return null;
    }
}
