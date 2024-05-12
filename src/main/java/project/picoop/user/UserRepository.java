package project.picoop.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import project.picoop.user.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email); // esto funciona? cambiar el nombre a findByUsername si funciona

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.credits = :credits WHERE u.id = :userId")
    void setCurrentUserCredits(Integer userId, int credits);
}
