package com.jobfinder.dto.enterprise;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnterpriseRequest {

    @NotEmpty(message = "Entrez le nom de votre entreprise")
    private String name;

    @NotEmpty(message = "Entrez une adresse mail pour votre entreprise")
    private String email;

    @NotEmpty(message = "Renseignez un mot de passe")
    @Size(min = 4, max = 20, message = "Votre mot de passe doit comprendre entre quatre(4) et vingt(20) caractères")
    private String pass;

    @NotEmpty(message = "Renseignez une confirmation du mot de passe")
    @Size(min = 4, max = 20, message = "Votre mot de passe doit comprendre entre quatre(4) et vingt(20) caractères")
    private String cPass;

    private String description;

    private String siteLink;

    @NotEmpty(message = "Quel est le secteur d'activité de votre entreprise")
    private String sector;

    @NotNull(message = "Renseignez un numéro de téléphone")
    private Integer telephone;

    private MultipartFile logo;
}
