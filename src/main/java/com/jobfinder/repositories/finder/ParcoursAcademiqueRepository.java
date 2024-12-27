package com.jobfinder.repositories.finder;

import com.jobfinder.entities.finder.Finder;
import com.jobfinder.entities.finder.ParcoursAcademique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParcoursAcademiqueRepository extends JpaRepository<ParcoursAcademique, Integer> {

    List<ParcoursAcademique> findAllByFinderFinderId(Integer finderId);

    Optional<ParcoursAcademique> findByParcoursId(Integer parcoursId);

    List<ParcoursAcademique> findAllByFinderAndEtablissementIgnoreCaseAndAndDebutAnneeGreaterThanEqualAndFinAnneeLessThanEqual(Finder finder, String etablissement, Integer debut, Integer fin);

}
