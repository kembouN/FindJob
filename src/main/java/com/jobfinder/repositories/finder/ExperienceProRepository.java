package com.jobfinder.repositories.finder;

import com.jobfinder.entities.finder.ExperiencePro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExperienceProRepository extends JpaRepository<ExperiencePro, Integer> {

    List<ExperiencePro> findAllByFinderFinderId(Integer finderId);

    Optional<ExperiencePro> findByExperienceId(Integer experienceId);

    int countAllByFinderFinderIdAndEntrepriseIgnoreCaseAndDateDebutGreaterThanEqualAndDateFinLessThanEqual(Integer finderId, String enterprise, LocalDate startDate, LocalDate endDate);
}
