package project.picoop.petition.model;

import java.time.LocalDate;

import lombok.Data;
import project.picoop.user.model.UserEntity;

/**
 * @author mguaitav
 */
@Data
public class PetitionDto {

    private Long petitionId;

    private String petitionTitle;

    private String petitionDescription;

    private LocalDate petitionDate;

    private UserEntity user;

    public Long getPetitionId() {
        return petitionId;
    }

    public void setPetitionId(Long petitionId) {
        this.petitionId = petitionId;
    }

    public String getPetitionTitle() {
        return petitionTitle;
    }

    public void setPetitionTitle(String petitionTitle) {
        this.petitionTitle = petitionTitle;
    }

    public String getPetitionDescription() {
        return petitionDescription;
    }

    public void setPetitionDescription(String petitionDescription) {
        this.petitionDescription = petitionDescription;
    }

    public LocalDate getPetitionDate() {
        return petitionDate;
    }

    public void setPetitionDate(LocalDate petitionDate) {
        this.petitionDate = petitionDate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
