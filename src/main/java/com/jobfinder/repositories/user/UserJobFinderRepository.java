package com.jobfinder.repositories.user;

import com.jobfinder.entities.user.UserJobFinder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJobFinderRepository extends JpaRepository<UserJobFinder, Integer> {

    UserJobFinder findUserJobFinderByUsername(String username);

    Optional<UserJobFinder> findByUsername(String username);

    Optional<UserJobFinder> findByAccountId(Integer userId);
}
