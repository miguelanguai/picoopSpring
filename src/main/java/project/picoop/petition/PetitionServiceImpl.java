package project.picoop.petition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import project.picoop.petition.model.PetitionDto;
import project.picoop.petition.model.PetitionEntity;
import project.picoop.user.UserService;
import project.picoop.user.model.UserEntity;

@Service
@Transactional
public class PetitionServiceImpl implements PetitionService {

    @Autowired
    PetitionRepository petitionRepository;

    @Autowired
    UserService userService;

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
    public boolean hasImage(Long petitionId) {
        return petitionRepository.findByPetitionId(petitionId);
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
        if (dto.getUser() == null) {
            UserEntity user = userService.getCurrentUser();
            petition.setUser(user);
        } else {
            petition.setUser(dto.getUser());
        }

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

    /**
     * {@inheritDoc}
     */
    @Override
    public long getUploadedPetitions() {
        return petitionRepository.count();
    }

}
