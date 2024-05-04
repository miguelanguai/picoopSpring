package project.picoop.image;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import project.picoop.image.model.ImageEntity;

/**
 * @author mguaitav
 */
public interface ImageService {

    /**
     * Uploads an image to the file system
     * 
     * @param file
     * 
     * @param imageRoute
     * @return
     */
    public void uploadImage(MultipartFile file, Integer user_id, Long petition_id, String imgTitle, String img_type,
            String img_description, LocalDate img_uploadingDate, String img_stage, String img_whyDenied)
            throws IOException;

    /**
     * gets a list of all images in the database
     * 
     */
    public List<ImageEntity> findAll();

}
