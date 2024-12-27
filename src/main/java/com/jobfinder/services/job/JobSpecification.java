package com.jobfinder.services.job;

import com.jobfinder.entities.finder.Finder;
import com.jobfinder.entities.job.Job;
import com.jobfinder.entities.job.Localisation;
import com.jobfinder.entities.job.Mission;
import com.jobfinder.entities.job.TypeContrat;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class JobSpecification {

    public static Specification<Job> jobTitleEquals(String jobTitle){
        return ((root, query, criteriaBuilder) -> {
            if (jobTitle == null || jobTitle.isEmpty() ){
                return null;
            }

            return criteriaBuilder.like(criteriaBuilder.lower(root.get("jobTitle")), "%"+jobTitle.toLowerCase()+"%");
        });
    }

    public static Specification<Job> isFullTime(Boolean fullTime){
        return ((root, query, criteriaBuilder) -> {
            if (fullTime == null){
                return null;
            }
            return criteriaBuilder.equal(root.get("isFullTime"), fullTime);
        });
    }

    public static Specification<Job> isRemote(Boolean remote){
        return ((root, query, criteriaBuilder) -> {
            if (remote == null){
                return null;
            }
            return criteriaBuilder.equal(root.get("isRemote"), remote);
        });
    }

    public static Specification<Job> salaryEqual(Integer salary){
        return ((root, query, criteriaBuilder) -> {
            if (salary == null){
                return null;
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("salaire"), salary);
        });
    }

    public static Specification<Job> domaineEquals(String domaine){
        return ((root, query, criteriaBuilder) -> {
            if (domaine == null || domaine.isEmpty()){
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("isFullTime")), "%"+domaine.toLowerCase()+"%");
        });
    }

    public static Specification<Job> typeContratEquals(String typeContrat){
        return (root, query, criteriaBuilder) -> {
            if (typeContrat == null || typeContrat.isEmpty()){
                return null;
            }

            Join<Job, TypeContrat> contratJobJoin = root.join("typeContrat", JoinType.INNER);
            return criteriaBuilder.like(criteriaBuilder.lower(contratJobJoin.get("libelle")), "%"+typeContrat.toLowerCase()+"%");
        };
    }

    public static Specification<Job> jobPublisherEquals(String name){
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()){
                return null;
            }

            Join<Job, Finder> jobPublisherJoin = root.join("publisher", JoinType.INNER);
            return criteriaBuilder.like(criteriaBuilder.lower(jobPublisherJoin.get("nom")), "%"+name.toLowerCase()+"%");
        };
    }

    public static Specification<Job> fetchJobExigence(){
        return (root, query, criteriaBuilder) -> {
            if(query.getResultType() != Long.class){
                root.fetch("exigences", JoinType.INNER);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Job> fetchJobMission(){
        return (root, query, criteriaBuilder) -> {
            if(query.getResultType() != Long.class){
                root.fetch("missions", JoinType.INNER);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Job> fetchNiveauRequis(){
        return (root, query, criteriaBuilder) -> {
            if(query.getResultType() != Long.class){
                root.fetch("niveauRequis", JoinType.INNER);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Job> jobLocalisationEquals(String localisation){
        return (root, query, criteriaBuilder) -> {
            if(localisation == null || localisation.isEmpty()){
                return null;
            }

            Join<Job, Localisation> jobLocalisationJoin = root.join("job_id", JoinType.LEFT);
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(jobLocalisationJoin.get("pays")), "%"+localisation.toLowerCase()+"%" ),
                    criteriaBuilder.like(criteriaBuilder.lower(jobLocalisationJoin.get("ville")), "%"+localisation.toLowerCase()+"%")
                    );
        };
    }
}
