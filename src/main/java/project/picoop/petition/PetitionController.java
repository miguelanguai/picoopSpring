package project.picoop.petition;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.picoop.petition.model.PetitionDto;
import project.picoop.petition.model.PetitionEntity;

/**
 * @author mguaitav
 */
@RequestMapping(value = "")
@RestController
public class PetitionController {

    @Autowired
    PetitionService petitionService;

    @Autowired
    ModelMapper mapper;

    /**
     * Método para recuperar todas las {@link PetitionEntity}
     *
     * @return {@link List} de {@link PetitionDto}
     */
    @RequestMapping(path = "public/petitions", method = RequestMethod.GET)
    public List<PetitionDto> findAll() {

        List<PetitionEntity> petitions = this.petitionService.findAll();

        return petitions.stream().map(e -> mapper.map(e, PetitionDto.class)).collect(Collectors.toList());
    }

    /**
     * Método para guardar una {@link PetitionEntity}
     */
    @RequestMapping(path = { "user/petitions", "user/petitions/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody PetitionDto dto) {

        this.petitionService.save(id, dto);
    }

    /**
     * Método para borrar una {@link PetitionEntity}
     */
    @RequestMapping(path = "user/petitions/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id", required = false) Long id) throws Exception {

        this.petitionService.delete(id);
    }
}
