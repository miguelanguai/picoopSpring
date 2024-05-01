package project.picoop.petition;

import org.springframework.data.jpa.repository.JpaRepository;

import project.picoop.petition.model.PetitionEntity;

public interface PetitionRepository extends JpaRepository<PetitionEntity, Long> {

}
