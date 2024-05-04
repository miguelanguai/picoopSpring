package project.picoop.image.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import project.picoop.petition.model.PetitionEntity;
import project.picoop.user.model.UserEntity;

/**
 * @author mguaitav
 */
@Entity
@Table(name = "img")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "petition_id", referencedColumnName = "petition_id")
    private PetitionEntity petition;

    @Column(name = "img_title", nullable = false)
    private String imgTitle;

    @Column(name = "img_type", nullable = false)
    private String img_type;
    // para conocer el formato de la imagen

    @Column(name = "img_description", nullable = false)
    private String img_description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "img_uploadingDate")
    private LocalDate img_uploadingDate;

    @Column(name = "img_stage")
    private String img_stage;

    @Column(name = "img_whyDenied")
    private String img_whyDenied;

    @Lob
    /*
     * para gestionar archivos grandes en db para almacenar en una columna tipo LOB
     */
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    // GETTERS & SETTERS

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

    public void setPetition(PetitionEntity petition) {
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
