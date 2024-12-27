package com.jobfinder.dto.finder;

import com.jobfinder.entities.finder.ParcoursAcademique;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParcoursAcademiqueResponse {

    List<ParcoursAcademique> parcoursAcademiques;

    String unsavedErrorMessage;
}
