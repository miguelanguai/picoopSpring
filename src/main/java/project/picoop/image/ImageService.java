package project.picoop.image;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author mguaitav
 */
public interface ImageService {

    /**
     * Uploads an image to the file system
     * 
     * @param imageRoute
     * @return
     */
    public String uploadImage(MultipartFile file) throws IOException;

    /**
     * Downloads an image from the file system
     * 
     * @param filename name of the file
     */
    public byte[] downloadImage(String filename);

}
