package project.picoop.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import project.picoop.user.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
