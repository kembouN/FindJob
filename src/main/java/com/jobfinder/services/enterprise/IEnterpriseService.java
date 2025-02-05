package com.jobfinder.services.enterprise;

import com.jobfinder.dto.enterprise.EnterpriseRequest;
import com.jobfinder.dto.enterprise.UpdateEnterpriseRequest;
import com.jobfinder.entities.enterprise.Enterprise;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEnterpriseService {

    Enterprise addEnterprise(EnterpriseRequest request);

    Enterprise updateEnterprise(Integer enterpriseId, UpdateEnterpriseRequest request);

    List<Enterprise> findEnterprisesBySector(Integer finderId);


    void addEnterpriseLogo(Integer enterpriseId, MultipartFile logo);
}
