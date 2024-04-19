package project.picoop.user;

import org.springframework.data.repository.CrudRepository;

import project.picoop.user.model.UserEntity;

/**
 * @author mguaitav
 *
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {

}