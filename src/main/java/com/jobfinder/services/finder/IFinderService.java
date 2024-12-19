package com.jobfinder.services.finder;

import com.jobfinder.dto.finder.FinderResponse;
import com.jobfinder.dto.finder.RegisterFinder;
import com.jobfinder.dto.finder.UpdateFinderRequest;
import com.jobfinder.dto.user.UserDto;
import com.jobfinder.entities.finder.Finder;
import org.hibernate.sql.Delete;

public interface IFinderService {

    Finder createFinder(RegisterFinder request);

    Finder getAllFinders();

    FinderResponse getSpecificFinder(Integer finderId);

    FinderResponse updateFinder(Integer finderId, UpdateFinderRequest request);

    void deleteFinder(Integer finderId);
}
