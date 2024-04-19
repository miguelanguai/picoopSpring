package project.picoop.user;

import java.util.List;

import project.picoop.user.model.UserEntity;
import project.picoop.user.model.UserDto;

/**
 * @author mguaitav
 * 
 */
public interface UserService {

    /**
     * Recupera una {@link UserEntity} a partir de su ID
     *
     * @param id PK de la entidad
     * @return {@link UserEntity}
     */
    UserEntity get(Long id);

    /**
     * Método para recuperar todas las {@link UserEntity}
     *
     * @return {@link List} de {@link UserEntity}
     */
    List<UserEntity> findAll();

    /**
     * Método para crear o actualizar una {@link UserEntity}
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, UserDto dto);

    /**
     * Método para borrar una {@link UserEntity}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

}