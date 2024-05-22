package project.picoop.petition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.picoop.petition.model.PetitionEntity;

public interface PetitionRepository extends JpaRepository<PetitionEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM ImageEntity i WHERE i.petition.id = :petitionId")
    boolean findByPetitionId(@Param("petitionId") Long petitionId);

}
