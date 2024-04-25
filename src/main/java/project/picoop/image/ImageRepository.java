package project.picoop.image;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import project.picoop.image.model.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    Optional<ImageEntity> findByImgTitle(String imgTitle);
}
