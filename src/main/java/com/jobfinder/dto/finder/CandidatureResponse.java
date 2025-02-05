package com.jobfinder.dto.finder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidatureResponse {

    private Integer jobId;

    private String jobPublisherName;

    private MultipartFile jobPublisherImage;

    private LocalDate dateCadidature;

}
