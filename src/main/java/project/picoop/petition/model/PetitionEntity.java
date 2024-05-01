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

}
