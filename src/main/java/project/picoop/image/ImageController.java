package project.picoop.image;

import java.io.IOException;
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
    public void uploadImage(@RequestParam MultipartFile file, @RequestParam Long petition_id,
            @RequestParam String imgTitle, @RequestParam String img_type, @RequestParam String img_description,
            @RequestParam String img_stage, @RequestParam String img_whyDenied) throws IOException {
        imageService.uploadImage(file, petition_id, imgTitle, img_type, img_description, img_stage, img_whyDenied);
    }

    /**
     * calls imageService.findAll to return all images to a List
     * 
     * @return List of Images
     */
    @RequestMapping(path = "/public/image", method = RequestMethod.GET)
    public List<ImageDto> findAll() {
        List<ImageEntity> images = this.imageService.findAll();
        return images.stream().map(e -> mapper.map(e, ImageDto.class)).collect(Collectors.toList());
    }

    /**
     * calls imageService.findFiltered to return a List of images filtered by name
     * or description
     * 
     * @param text which is going to be the filter
     * @return List of Images
     */
    @RequestMapping(path = "/public/image/filtered", method = RequestMethod.GET)
    public List<ImageDto> findFiltered(@RequestParam(value = "text", required = true) String text) {
        List<ImageEntity> images = this.imageService.findFiltered(text);
        return images.stream().map(e -> mapper.map(e, ImageDto.class)).collect(Collectors.toList());
    }
}
