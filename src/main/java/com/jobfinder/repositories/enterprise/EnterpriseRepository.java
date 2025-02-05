package com.jobfinder.repositories.enterprise;

import com.jobfinder.entities.enterprise.Enterprise;
import com.jobfinder.entities.user.UserJobFinder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer> {

    Optional<Enterprise> findByEnterpriseId(Integer enterpriseId);

    Enterprise findByUser(UserJobFinder user);

    List<Enterprise> findAllBySector(String sector);
}
