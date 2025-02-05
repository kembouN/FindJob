package com.jobfinder.dto.enterprise;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEnterpriseRequest {

    @NotEmpty(message = "Entrez le nom de votre entreprise")
    private String name;

    private String description;

    private String siteLink;

    @NotEmpty(message = "Quel est le secteur d'activité de votre entreprise")
    private String sector;

    @NotNull(message = "Renseignez un numéro de téléphone")
    private Integer telephone;

    private MultipartFile logo;

}
