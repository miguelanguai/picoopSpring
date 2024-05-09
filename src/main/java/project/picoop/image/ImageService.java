package project.picoop.image;

import java.io.IOException;
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
    public void uploadImage(MultipartFile file, Long petition_id, String imgTitle, String img_type,
            String img_description, String img_stage, String img_whyDenied) throws IOException;

    /**
     * gets a list of all images in the database
     * 
     */
    public List<ImageEntity> findAll();

}
