package com.jobfinder.repositories.finder;

import com.jobfinder.entities.finder.Finder;
import com.jobfinder.entities.user.UserJobFinder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

    Finder findByUser(UserJobFinder user);

    Optional<Finder> findByFinderId(Integer finderId);
}
