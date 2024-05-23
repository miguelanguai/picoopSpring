package project.picoop.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import project.picoop.user.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.credits = :credits WHERE u.id = :userId")
    void setCurrentUserCredits(Integer userId, int credits);

    @Query("SELECT u.role FROM UserEntity u WHERE u.email = :email")
    String findUserRoleByEmail(@Param("email") String email);
}
