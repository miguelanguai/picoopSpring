package project.picoop.image;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import project.picoop.image.model.ImageDto;
import project.picoop.image.model.ImageEntity;

@RestController
@RequestMapping("")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ModelMapper mapper;

    /**
     * calls service.uploadImage to store image in db
     * 
     * @param imageDto difference with imageEntity is that property image here es un
     *                 MultipartFile
     * @throws IOException
     */
    @RequestMapping(path = "/user/image", method = RequestMethod.POST)
    public void uploadImage(@RequestParam MultipartFile file, @RequestParam Integer user_id,
            @RequestParam Long petition_id, @RequestParam String imgTitle, @RequestParam String img_type,
            @RequestParam String img_description, @RequestParam LocalDate img_uploadingDate,
            @RequestParam String img_stage, @RequestParam String img_whyDenied) throws IOException {
        imageService.uploadImage(file, user_id, petition_id, imgTitle, img_type, img_description, img_uploadingDate,
                img_stage, img_whyDenied);
    }

    /**
     * calls imageService.findAll to return all images to a List
     * 
     * @return List of Images
     */
    @RequestMapping(path = "/user/image", method = RequestMethod.GET)
    public List<ImageDto> findAll() {
        List<ImageEntity> images = this.imageService.findAll();
        return images.stream().map(e -> mapper.map(e, ImageDto.class)).collect(Collectors.toList());
    }

}
