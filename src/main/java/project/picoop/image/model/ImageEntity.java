package project.picoop.image.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "petition_id", nullable = false)
    private Long petition_id;

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
    @Column(name = "img_route")
    private byte[] imageData;

    // GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getPetition_id() {
        return petition_id;
    }

    public void setPetition_id(Long petition_id) {
        this.petition_id = petition_id;
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

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

}
