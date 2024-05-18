package project.picoop.petition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import project.picoop.petition.model.PetitionDto;
import project.picoop.petition.model.PetitionEntity;
import project.picoop.user.model.UserEntity;

@ExtendWith(MockitoExtension.class)
public class PetitionTest {

    @Mock
    private PetitionRepository petitionRepository;

    @InjectMocks
    private PetitionServiceImpl petitionService;

    public static final String PETITION_TITLE = "PETITION TITLE 1";
    public static final String PETITION_DESCRIPTION = "PETITION DESCRIPTION 1";
    static LocalDate now = LocalDate.now();
    public static final LocalDate PETITION_CREATION_DATE = now;
    static UserEntity user = createUser();
    public static final UserEntity PETITION_USER = user;

    public static final Long EXISTS_PETITION_ID = 1L;
    public static final Long NOT_EXISTS_PETITION_ID = 0L;

    /**
     * Method to generate User for tests
     * 
     * @return
     */
    private static UserEntity createUser() {
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setEmail("usuario@mail.com");
        user.setPassword("contraseña");
        user.setRole("USER");
        user.setCredits(100);
        return user;
    }

    /**
     * Verifies that findAll returns all petitions
     */
    @Test
    public void findAllShouldReturnAllPetitions() {
        // lista vacia de petitions
        List<PetitionEntity> list = new ArrayList<>();
        // se añade a la lista un mock de petition
        list.add(mock(PetitionEntity.class));
        // cuando se ejecute findAll del petitionRepository, devolver la lista
        when(petitionRepository.findAll()).thenReturn(list);
        // se llama al findAll del petitionService
        List<PetitionEntity> petitions = petitionService.findAll();
        // se verifica qeu la lista petitions no sea nula
        assertNotNull(petitions);
        // se verifica que el tamaño de la lista sea de una peticion
        assertEquals(1, petitions.size());
    }

    /**
     * Verifies saving a petition works
     */
    @Test
    public void saveNotExistsPetitionIdShouldInsert() {
        // Se genera una peticion nueva
        PetitionDto petitionDto = new PetitionDto();
        // se hacen los setters de los atributos definidos arriba
        petitionDto.setPetitionTitle(PETITION_TITLE);
        petitionDto.setPetitionDescription(PETITION_DESCRIPTION);
        petitionDto.setPetitionDate(PETITION_CREATION_DATE);
        petitionDto.setUser(PETITION_USER);

        // permite realizar aserciones sobre los atributos del objeto capturado para
        // garantizar que se pasaron correctamente al método mockito.
        ArgumentCaptor<PetitionEntity> petition = ArgumentCaptor.forClass(PetitionEntity.class);

        // guarda la peticion con el service
        petitionService.save(null, petitionDto);

        // verifica que se haya guardado la peticion en el repository
        verify(petitionRepository).save(petition.capture());

        // comparacion entre las variables que hemos metido y las variables del objeto
        // que se ha guardado
        assertEquals(PETITION_TITLE, petition.getValue().getPetitionTitle());
        assertEquals(PETITION_DESCRIPTION, petition.getValue().getPetitionDescription());
        assertEquals(PETITION_CREATION_DATE, petition.getValue().getPetitionDate());
        assertEquals(PETITION_USER, petition.getValue().getUser());
    }

    /**
     * Verifies that deleting a petition giving a petition id works
     */
    @Test
    public void deleteExistsPetitionIdShouldDelete() throws Exception {
        // Genera peticion con mock
        PetitionEntity petition = mock(PetitionEntity.class);
        // si es encontrada en la db, devuelvela
        when(petitionRepository.findById(EXISTS_PETITION_ID)).thenReturn(Optional.of(petition));
        // borrala
        petitionService.delete(EXISTS_PETITION_ID);
        // verifica que el borrado se ha hecho
        verify(petitionRepository).deleteById(EXISTS_PETITION_ID);
    }

    /**
     * Verifies that getting a petition by its ID returns that petition
     */
    @Test
    public void getExistsPetitionIdShouldReturnPetition() {

        PetitionEntity petition = mock(PetitionEntity.class);
        when(petition.getPetitionId()).thenReturn(EXISTS_PETITION_ID);
        when(petitionRepository.findById(EXISTS_PETITION_ID)).thenReturn(Optional.of(petition));

        PetitionEntity petitionResponse = petitionService.findPetitionById(EXISTS_PETITION_ID);

        assertNotNull(petitionResponse);
        assertEquals(EXISTS_PETITION_ID, petition.getPetitionId());
    }

    /**
     * Verifies that, while getting a petition without ID, null is returned
     */
    @Test
    public void getNotExistsPetitionEntityIdShouldReturnNull() {

        when(petitionRepository.findById(NOT_EXISTS_PETITION_ID)).thenReturn(Optional.empty());

        PetitionEntity petition = petitionService.findPetitionById(NOT_EXISTS_PETITION_ID);

        assertNull(petition);
    }
}
