package project.picoop.petition.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import project.picoop.user.model.UserEntity;

/**
 * @author mguaitav
 */
@Data
@Entity
@Table(name = "petition")
public class PetitionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "petition_id")
    private Long petitionId;

    @Column(name = "petition_title")
    private String petitionTitle;

    @Column(name = "petition_description")
    private String petitionDescription;

    @Column(name = "petition_date")
    private LocalDate petitionDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    /**
     * @return petitionId
     */
    public Long getPetitionId() {
        return petitionId;
    }

    /**
     * @param petitionId new value of {@link #getPetitionId}.
     */
    public void setPetitionId(Long petitionId) {
        this.petitionId = petitionId;
    }

    /**
     * @return petitionTitle
     */
    public String getPetitionTitle() {
        return petitionTitle;
    }

    /**
     * @param petitionTitle new value of {@link #getPetitionTitle}.
     */
    public void setPetitionTitle(String petitionTitle) {
        this.petitionTitle = petitionTitle;
    }

    /**
     * @return petitionDescription
     */
    public String getPetitionDescription() {
        return petitionDescription;
    }

    /**
     * @param petitionDescription new value of {@link #getPetitionDescription}.
     */
    public void setPetitionDescription(String petitionDescription) {
        this.petitionDescription = petitionDescription;
    }

    /**
     * @return petitionDate
     */
    public LocalDate getPetitionDate() {
        return petitionDate;
    }

    /**
     * @param petitionDate new value of {@link #getPetitionDate}.
     */
    public void setPetitionDate(LocalDate petitionDate) {
        this.petitionDate = petitionDate;
    }

    /**
     * @return User
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * @param user new value of {@link #getUser}.
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

}
