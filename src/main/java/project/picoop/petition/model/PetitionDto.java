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
}
