package project.picoop.image;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import project.picoop.image.model.ImageEntity;
import project.picoop.petition.PetitionService;
import project.picoop.user.UserService;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    UserService userService;

    @Autowired
    PetitionService petitionService;

    /**
     * {@inheritDoc}
     */
    public void uploadImage(MultipartFile file, Integer user_id, Long petition_id, String imgTitle, String img_type,
            String img_description, LocalDate img_uploadingDate, String img_stage, String img_whyDenied)
            throws IOException {
        // convert dto to imageEntity
        ImageEntity imageEntity = new ImageEntity();

        imageEntity.setUser(userService.findUserById(user_id).orElse(null));
        imageEntity.setPetition(petitionService.findPetitionById(petition_id));
        imageEntity.setImgTitle(imgTitle);
        imageEntity.setImg_type(img_type);
        imageEntity.setImg_description(img_description);
        imageEntity.setImg_uploadingDate(img_uploadingDate);
        imageEntity.setImg_stage(img_stage);
        imageEntity.setImg_whyDenied(img_whyDenied);

        // in the conversion, set image from MultipartFile to string
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a a valid file");
        }
        imageEntity.setImage(Base64.getEncoder().encodeToString(file.getBytes()));

        // call the repository to store
        imageRepository.save(imageEntity);
        System.out.println("Image saved in the database");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImageEntity> findAll() {
        return (List<ImageEntity>) this.imageRepository.findAll();
    }
}
