package project.picoop.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.picoop.admin.data.WebData;
import project.picoop.image.ImageService;
import project.picoop.petition.PetitionService;
import project.picoop.user.UserService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    ImageService imageService;

    @Autowired
    PetitionService petitionService;

    @Autowired
    UserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    public WebData getWebData() {
        WebData webData = new WebData();
        webData.setUploadedImages(imageService.getUploadedImages());
        webData.setUploadedPetitions(petitionService.getUploadedPetitions());
        webData.setRegisteredUsers(userService.getRegisteredUsers());
        return webData;
    }

}
