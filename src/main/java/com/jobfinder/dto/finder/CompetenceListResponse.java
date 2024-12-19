package com.jobfinder.dto.finder;

import com.jobfinder.entities.finder.Competence;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompetenceListResponse {

    private FinderResponse finder;

    private List<Competence> competences;
}
