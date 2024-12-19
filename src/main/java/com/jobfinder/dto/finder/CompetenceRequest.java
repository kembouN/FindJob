package com.jobfinder.dto.finder;

import com.jobfinder.entities.finder.Competence;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetenceRequest {

    @NotNull(message = "Veuillez renseigner au moins une compétence")
    private List<String> libelles;

    private Integer finderId;
}
