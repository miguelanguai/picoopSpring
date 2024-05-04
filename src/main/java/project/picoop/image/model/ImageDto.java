package project.picoop.image.model;

import java.time.LocalDate;

import project.picoop.petition.model.PetitionEntity;
import project.picoop.user.model.UserEntity;

/**
 * @author mguaitav
 */
public class ImageDto {
    private Long id;

    private UserEntity user;

    private PetitionEntity petition;

    private String imgTitle;

    private String img_type;

    private String img_description;

    private LocalDate img_uploadingDate;

    private String img_stage;

    private String img_whyDenied;

    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public PetitionEntity getPetition() {
        return petition;
    }

    public void setPetition_id(PetitionEntity petition) {
        this.petition = petition;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    public String getImg_type() {
        return img_type;
    }

    public void setImg_type(String img_type) {
        this.img_type = img_type;
    }

    public String getImg_description() {
        return img_description;
    }

    public void setImg_description(String img_description) {
        this.img_description = img_description;
    }

    public LocalDate getImg_uploadingDate() {
        return img_uploadingDate;
    }

    public void setImg_uploadingDate(LocalDate img_uploadingDate) {
        this.img_uploadingDate = img_uploadingDate;
    }

    public String getImg_stage() {
        return img_stage;
    }

    public void setImg_stage(String img_stage) {
        this.img_stage = img_stage;
    }

    public String getImg_whyDenied() {
        return img_whyDenied;
    }

    public void setImg_whyDenied(String img_whyDenied) {
        this.img_whyDenied = img_whyDenied;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
