package com.jobfinder.services.job.impl;

import com.jobfinder.dto.job.*;
import com.jobfinder.entities.enterprise.Enterprise;
import com.jobfinder.entities.finder.Finder;
import com.jobfinder.entities.job.*;
import com.jobfinder.exception.OperationNonPermittedException;
import com.jobfinder.repositories.enterprise.EnterpriseRepository;
import com.jobfinder.services.job.IJobService;
import com.jobfinder.repositories.finder.DiplomeRepository;
import com.jobfinder.repositories.finder.FinderRepository;
import com.jobfinder.repositories.job.*;
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
    private ObjectValidator<MissionRequest> missionValidator;

    @Autowired
    private ObjectValidator<ExigenceRequest> exigenceValidator;

    @Autowired
    private ObjectValidator<NiveauRequisRequest> levelValidator;

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

    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private JobRepository jobRepository;

    @Override
    @Transactional
    public Job addJob(JobRequest request) {
        jobValidator.validate(request);
        Finder finder = finderRepository.findByFinderId(request.getUserId()).get();

        Enterprise enterprise = enterpriseRepository.findByEnterpriseId(request.getUserId()).get();

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
                        .totalCandidat(0)
                        .description(request.getDescription().trim())
                        .delai(request.getDelai())
                        .finder(finder)
                        .enterprise(enterprise)
                        .build()
        );

        localisationService.addLocalisation(
                LocalisationRequest.builder()
                        .town(request.getTown())
                        .country(request.getCountry())
                        .job(savedJob)
                        .build()
        );


        addExigence(ExigenceRequest.builder()
                .exigences(request.getExigences())
                .job(savedJob)
                .build()
        );

        addMision(MissionRequest.builder()
                .missions(request.getMissions())
                .job(savedJob)
                .build()
        );

        addStudyLevel(NiveauRequisRequest.builder()
                .studyLevel(request.getStudyLevels())
                .job(savedJob)
                .build()
        );

        return savedJob;

    }

    @Override
    public List<JobResponse> getJobByResearch(String jobTitle, Boolean isFullTime, Boolean isRemote, Integer salary, String domaine, String typeContrat, String publisher, String localisation) {
        return getJobResponses(jobTitle, isFullTime, isRemote, salary, typeContrat, publisher, domaine, localisation);
    }

    @Override
    public List<JobResponse> getJobByFinderWithFilter(Integer finderId,String jobTitle, Boolean isFullTime, Boolean isRemote, Integer salary, String typeContrat, String publisher) {
        Finder finder = finderRepository.findByFinderId(finderId).orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable"));
        List<JobResponse> result;
        if (!finder.getUser().isEnterprise()){
            String sector = finder.getSector().trim();
            String ville = finder.getLocalisation().getVille();
            result = getJobResponses(jobTitle, isFullTime, isRemote, salary, typeContrat, publisher, sector, ville);
        }else {
            Specification<Job> jobSpecification = Specification
                    .where(JobSpecification.fetchJobMission())
                    .and(JobSpecification.fetchJobExigence())
                    .and(JobSpecification.fetchNiveauRequis())
                    .and(JobSpecification.publiserEquals(finder))
                    .and(
                            JobSpecification.jobTitleEquals(jobTitle)
                                    .or(JobSpecification.isFullTime(isFullTime))
                                    .or(JobSpecification.isRemote(isRemote))
                                    .or(JobSpecification.salaryEqual(salary))
                                    .or(JobSpecification.typeContratEquals(typeContrat))
                                    .or(JobSpecification.jobPublisherEquals(publisher))
                    );
            List<Job> jobs = jobRepository.findAll(jobSpecification);
            result = getJobResponseArray(jobs);
        }
        return result;

    }

    @Override
    public Void deleteJob(Integer jobId) {
        return null;
    }

    @Override
    @Transactional
    public List<Mission> addMision(MissionRequest request) {
        missionValidator.validate(request);
        List<Mission> result = new ArrayList<>();
        Job job = checkJob(request.getJob(), request.getJobId());
        for (String mission : request.getMissions()) {
            int rang = request.getMissions().indexOf(mission);
            if (missionRepository.countAllByJobAndDescriptionIgnoreCase(job, mission.trim()) != 0){
                throw new OperationNonPermittedException("La mission n°"+rang+" est déjà attribué à ce job");
            }

            result.add(missionRepository.save(
                    Mission.builder()
                            .description(mission)
                            .job(job)
                            .build()
            ));
        }
        return result;
    }

    @Override
    @Transactional
    public List<Exigence> addExigence(ExigenceRequest request) {
        exigenceValidator.validate(request);
        List<Exigence> result = new ArrayList<>();
        Job existJob = checkJob(request.getJob(), request.getJobId());
        for (String exigence : request.getExigences()) {
            int rang = request.getExigences().indexOf(exigence);
            if (exigenceRepository.countAllByJobAndDescriptionIgnoreCase(existJob, exigence.trim()) !=0){
                throw new OperationNonPermittedException("L'exigence n°"+rang+" existe déjà pour ce job");
            }
            result.add(exigenceRepository.save(
                    Exigence.builder()
                            .description(exigence)
                            .job(existJob)
                            .build()
            ));
        }
        return result;
    }

    @Override
    @Transactional
    public List<NiveauRequis> addStudyLevel(NiveauRequisRequest request) {
        levelValidator.validate(request);
        List<NiveauRequis> result = new ArrayList<>();
        Job existJob = checkJob(request.getJob(), request.getJobId());
        for (NiveauRequisDto niveauRequis : request.getStudyLevel()){
            int rang = request.getStudyLevel().indexOf(niveauRequis);
            if (niveauRequisRepository.countAllByJobAndLibelleIgnoreCase(existJob, niveauRequis.getDescription()) != 0){
                throw new OperationNonPermittedException("Le niveau d'étude requis n°"+rang+" est déjà attribué à ce job");
            }

            result.add(niveauRequisRepository.save(
                    NiveauRequis.builder()
                            .diplome(diplomeRepository.findById(niveauRequis.getDiplomeId()).orElseThrow(() -> new EntityNotFoundException("Diplome introuvable")))
                            .libelle(niveauRequis.getDescription())
                            .job(existJob)
                            .build()
            ));
        }
        return result;
    }

    private Job checkJob(Job job, Integer jobId){
        Job existJob;
        if (job != null){
            existJob = job;
        } else if (jobId != null) {
            existJob = jobRepository.findById(jobId).orElseThrow(()
                    -> new EntityNotFoundException("Ce job n'esxiste pas"));
        }else {
            throw new OperationNonPermittedException("Le Job est introuvable");
        }
        return existJob;
    }

    private List<JobResponse> getJobResponseArray(List<Job> jobs) {
        List<JobResponse> result = new ArrayList<>();
        for (Job job : jobs){
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
                    .recruiter(job.getFinder().getNom().isEmpty() ? job.getEnterprise().getEnterpriseName() : job.getFinder().getNom())
                    .totalCandidat(job.getTotalCandidat())
                    .delai(job.getDelai())
                    .recruiterImage(job.getFinder().getPhotoProfil().length != 0 ? job.getFinder().getPhotoProfil() : job.getEnterprise().getProfil() )
                    .uploadDate(job.getCreatedAt())
                    .exigences(job.getExigences())
                    .missions(job.getMissions())
                    .levels(job.getNiveauRequis())
                    .localisation(job.getLocalisations())
                    .build());
        }
        return result;
    }

    private List<JobResponse> getJobResponses(String jobTitle, Boolean isFullTime, Boolean isRemote, Integer salary, String typeContrat, String publisher, String sector, String ville) {
        Specification<Job> jobSpecification = Specification
                .where(JobSpecification.fetchJobMission())
                .and(JobSpecification.fetchJobExigence())
                .and(JobSpecification.fetchNiveauRequis())
                .and(
                        JobSpecification.jobTitleEquals(jobTitle)
                                .or(JobSpecification.isFullTime(isFullTime))
                                .or(JobSpecification.isRemote(isRemote))
                                .or(JobSpecification.salaryEqual(salary))
                                .or(JobSpecification.domaineEquals(sector))
                                .or(JobSpecification.typeContratEquals(typeContrat))
                                .or(JobSpecification.jobPublisherEquals(publisher))
                                .or(JobSpecification.jobLocalisationEquals(ville))
                );
        List<Job> jobs = jobRepository.findAll(jobSpecification);

        List<JobResponse> result = new ArrayList<>();

        getJobResponseArray(jobs);
        return result;
    }

}
