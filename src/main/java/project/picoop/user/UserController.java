package project.picoop.user;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import project.picoop.user.model.UserDto;
import project.picoop.user.model.UserEntity;

/**
 * @author mguaitav
 *
 */
@Tag(name = "User", description = "API of User")
@RequestMapping(value = "/user")
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper mapper;

    /**
     * Método para recuperar todas las {@link UserEntity}
     *
     * @return {@link List} de {@link UserDto}
     */
    @Operation(summary = "Find", description = "Method that return a list of Users")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<UserDto> findAll() {

        List<UserEntity> categories = this.userService.findAll();

        return categories.stream().map(e -> mapper.map(e, UserDto.class)).collect(Collectors.toList());
    }

    /**
     * Método para crear o actualizar una {@link UserEntity}
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    @Operation(summary = "Save or Update", description = "Method that saves or updates a User")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody UserDto dto) {

        this.userService.save(id, dto);
    }

    /**
     * Método para borrar una {@link UserEntity}
     *
     * @param id PK de la entidad
     */
    @Operation(summary = "Delete", description = "Method that deletes a User")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {

        this.userService.delete(id);
    }

}