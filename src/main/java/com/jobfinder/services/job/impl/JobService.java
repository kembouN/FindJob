package com.jobfinder.services.job.impl;

import com.jobfinder.dto.job.JobRequest;
import com.jobfinder.dto.job.JobResponse;
import com.jobfinder.dto.job.LocalisationRequest;
import com.jobfinder.dto.job.NiveauRequisDto;
import com.jobfinder.entities.finder.Finder;
import com.jobfinder.entities.job.*;
import com.jobfinder.exception.OperationNonPermittedException;
import com.jobfinder.repositories.finder.DiplomeRepository;
import com.jobfinder.repositories.finder.FinderRepository;
import com.jobfinder.repositories.job.*;
import com.jobfinder.services.job.IJobService;
import com.jobfinder.services.job.ILocalisationService;
import com.jobfinder.services.job.JobSpecification;
import com.jobfinder.validator.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class JobService implements IJobService {

    @Autowired
    private ObjectValidator<JobRequest> jobValidator;

    @Autowired
    private TypeContratRepository contratRepository;

    @Autowired
    private DiplomeRepository diplomeRepository;

    @Autowired
    private NiveauRequisRepository niveauRequisRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private ExigenceRepository exigenceRepository;

    @Autowired
    private FinderRepository finderRepository;

    @Autowired
    private ILocalisationService localisationService;

    @Autowired
    private JobRepository jobRepository;

    @Override
    @Transactional
    public Job addJob(JobRequest request) {
        jobValidator.validate(request);
        Finder finder = finderRepository.findByFinderId(request.getFinderId())
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable"));

        TypeContrat contrat = contratRepository
                .findById(request.getTypeContratId())
                .orElseThrow(() -> new EntityNotFoundException("Le type de contrat choisi est introuvable"));

        Job savedJob = jobRepository.save(
                Job.builder()
                        .jobTitle(request.getJobTitle().trim())
                        .isFullTime(request.isFullTime())
                        .anneeExpMin(request.getExperienceMin())
                        .isRemote(request.isRemote())
                        .salaire(request.getSalary())
                        .domaine(request.getDomaine().trim())
                        .typeContrat(contrat)
                        .description(request.getDescription().trim())
                        .delai(request.getDelai())
                        .publisher(finder)
                        .build()
        );

        localisationService.addLocalisation(
                LocalisationRequest.builder()
                        .town(request.getTown())
                        .country(request.getCountry())
                        .job(savedJob)
                        .build()
        );

        for (String exigence : request.getExigences()) {
            int rang = request.getExigences().indexOf(exigence);
            if (exigenceRepository.countAllByJobAndDescriptionIgnoreCase(savedJob, exigence.trim()) !=0){
                throw new OperationNonPermittedException("L'exigence n°"+rang+" existe déjà pour ce job");
            }
            exigenceRepository.save(
                    Exigence.builder()
                            .description(exigence)
                            .job(savedJob)
                            .build()
            );
        }

        for (String mission : request.getMissions()
             ) {
            int rang = request.getMissions().indexOf(mission);
            if (missionRepository.countAllByJobAndDescriptionIgnoreCase(savedJob, mission.trim()) != 0){
                throw new OperationNonPermittedException("La mission n°"+rang+" est déjà attribué à ce job");
            }

            missionRepository.save(
                    Mission.builder()
                            .description(mission)
                            .job(savedJob)
                            .build()
            );
        }

        for (NiveauRequisDto niveauRequis : request.getStudyLevels()){
            int rang = request.getStudyLevels().indexOf(niveauRequis);
            if (niveauRequisRepository.countAllByJobAndLibelleIgnoreCase(savedJob, niveauRequis.getDescription()) != 0){
                throw new OperationNonPermittedException("Le niveau d'étude requis n°"+rang+" est déjà attribué à ce job");
            }

            niveauRequisRepository.save(
                    NiveauRequis.builder()
                            .diplome(diplomeRepository.findById(niveauRequis.getDiplomeId()).orElseThrow(() -> new EntityNotFoundException("Diplome introuvable")))
                            .libelle(niveauRequis.getDescription())
                            .job(savedJob)
                            .build()
            );
        }
        return savedJob;

    }

    @Override
    public List<JobResponse> getJobByResearch(String jobTitle, Boolean isFullTime, Boolean isRemote, Integer salary, String domaine, String typeContrat, String publisher, String localisation) {
        Specification<Job> jobSpecification = Specification
                .where(JobSpecification.fetchJobMission())
                .and(JobSpecification.fetchJobExigence())
                .and(JobSpecification.fetchNiveauRequis())
                .and(
                        JobSpecification.jobTitleEquals(jobTitle)
                                .or(JobSpecification.isFullTime(isFullTime))
                                .or(JobSpecification.isRemote(isRemote))
                                .or(JobSpecification.salaryEqual(salary))
                                .or(JobSpecification.domaineEquals(domaine))
                                .or(JobSpecification.typeContratEquals(typeContrat))
                                .or(JobSpecification.jobPublisherEquals(publisher))
                                .or(JobSpecification.jobLocalisationEquals(localisation))
                );

        List<Job> jobs = jobRepository.findAll(jobSpecification);

        List<JobResponse> result = new ArrayList<>();

        for (Job job : jobs) {
            result.add(JobResponse.builder()
                    .jobId(job.getJobId())
                    .jobTilte(job.getJobTitle())
                    .isFullTime(job.isFullTime())
                    .experienceMin(job.getAnneeExpMin())
                    .isRemote(job.isRemote())
                    .salary(job.getSalaire())
                    .domaine(job.getDomaine())
                    .typeContrat(job.getTypeContrat())
                    .jobDescription(job.getDescription())
                    .delai(job.getDelai())
                    .exigences(job.getExigences())
                    .missions(job.getMissions())
                    .levels(job.getNiveauRequis())
                    .localisation(job.getLocalisations())
                    .build());
        }
        return result;
    }

    @Override
    public Void deleteJob(Integer jobId) {
        return null;
    }
}
