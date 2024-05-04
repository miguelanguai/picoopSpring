package project.picoop.petition;

import java.util.List;

import project.picoop.petition.model.PetitionDto;
import project.picoop.petition.model.PetitionEntity;

public interface PetitionService {

    /**
     * returns a list of all petitions
     * 
     * @return
     */
    List<PetitionEntity> findAll();

    /**
     * creates or edits a petition
     * 
     * @param dto
     */
    void save(Long id, PetitionDto dto);

    /**
     * eliminates a petition
     */
    void delete(Long id) throws Exception;

    /**
     * finds a petition by id
     * 
     * @param petition_id
     * @return PetitionEntity
     */
    PetitionEntity findPetitionById(Long petition_id);

}
