package project.picoop.petition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import project.picoop.petition.model.PetitionDto;
import project.picoop.petition.model.PetitionEntity;

@Service
@Transactional
public class PetitionServiceImpl implements PetitionService {

    @Autowired
    PetitionRepository petitionRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PetitionEntity> findAll() {

        return (List<PetitionEntity>) petitionRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, PetitionDto dto) {

        PetitionEntity petition;

        if (id == null) {
            petition = new PetitionEntity();
        } else {
            petition = this.petitionRepository.findById(id).orElse(null);
        }

        petition.setPetitionTitle(dto.getPetitionTitle());
        petition.setPetitionDescription(dto.getPetitionDescription());
        petition.setPetitionDate(dto.getPetitionDate());
        petition.setUser(dto.getUser());

        this.petitionRepository.save(petition);

    }

    /**
     * {@inheritDoc}
     * 
     * @throws Exception
     */
    @Override
    public void delete(Long id) throws Exception {
        if (this.petitionRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not exists");
        }

        this.petitionRepository.deleteById(id);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PetitionEntity findPetitionById(Long petition_id) {

        return petitionRepository.findById(petition_id).orElse(null);
    }

}
