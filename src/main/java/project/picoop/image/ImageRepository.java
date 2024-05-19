package project.picoop.image;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.picoop.image.model.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    Optional<ImageEntity> findByImgTitle(String imgTitle);

    @Query("SELECT i FROM ImageEntity i WHERE i.imgTitle LIKE %:searchText% OR i.img_description LIKE %:searchText%")
    List<ImageEntity> findFiltered(@Param("searchText") String searchText);

}
