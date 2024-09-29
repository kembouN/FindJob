package com.jobfinder.services.finder;

import com.jobfinder.dto.finder.RegisterFinder;
import com.jobfinder.dto.user.UserDto;
import com.jobfinder.entities.finder.Finder;
import org.hibernate.sql.Delete;

public interface IFinderService {

    Finder createFinder(RegisterFinder request);

    Finder getAllFinders();

    Finder getSpecificFinder(Integer finderId);

    Finder updateFinder(Integer finderId, RegisterFinder request);

    void deleteFinder(Integer finderId);
}
