package project.picoop.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import project.picoop.user.model.UserEntity;
import project.picoop.user.model.UserDto;

/**
 * @author mguaitav
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity get(Long id) {

        return this.userRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserEntity> findAll() {

        return (List<UserEntity>) this.userRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, UserDto dto) {

        UserEntity user = null;

        if (id == null) {
            user = new UserEntity();
        } else {
            user = this.get(id);
        }

        user.setUsername(dto.getUsername());
        user.setMail(dto.getMail());
        user.setPswd(dto.getPswd());
        user.setCredits(dto.getCredits());

        this.userRepository.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.userRepository.deleteById(id);
    }

}