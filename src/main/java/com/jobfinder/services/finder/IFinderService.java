package com.jobfinder.services.finder;

import com.jobfinder.dto.finder.FinderResponse;
import com.jobfinder.dto.finder.RegisterFinder;
import com.jobfinder.dto.finder.UpdateFinderRequest;
import com.jobfinder.entities.finder.Finder;
import org.springframework.web.multipart.MultipartFile;

public interface IFinderService {

    Finder createFinder(RegisterFinder request);

    Finder getAllFindersByDomain();

    FinderResponse getSpecificFinder(Integer finderId);

    FinderResponse updateFinder(Integer finderId, UpdateFinderRequest request);

    void deleteFinder(Integer finderId);

    Void addProfilePicture(Integer finderId, MultipartFile profilePic);

    byte[] getFinderPicture(Integer finderId);
}
