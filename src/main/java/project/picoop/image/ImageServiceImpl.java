package project.picoop.image;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.picoop.image.model.ImageEntity;
import project.picoop.util.ImageUtils;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    /**
     * {@inheritDoc}
     */
    public String uploadImage(MultipartFile file) throws IOException {

        ImageEntity imageEntity = imageRepository.save(ImageEntity.builder().imgTitle(file.getOriginalFilename())
                .img_type(file.getContentType()).imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageEntity != null) {
            return "file uploaded successfully: " + file.getOriginalFilename();
        }

        return null;
    }

    public byte[] downloadImage(String fileName) {
        Optional<ImageEntity> dbImageEntity = imageRepository.findByImgTitle(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageEntity.get().getImageData());
        return images;
    }
}
