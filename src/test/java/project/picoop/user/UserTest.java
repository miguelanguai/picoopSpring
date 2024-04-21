package project.picoop.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import project.picoop.user.model.UserDto;
import project.picoop.user.model.UserEntity;

@ExtendWith(MockitoExtension.class)
public class UserTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void findAllShouldReturnAllUsers() {

        List<UserEntity> list = new ArrayList<>();
        list.add(mock(UserEntity.class));

        when(userRepository.findAll()).thenReturn(list);

        List<UserEntity> categories = userService.findAll();

        assertNotNull(categories);
        assertEquals(1, categories.size());
    }

    public static final String USER_NAME = "USER1";
    public static final String USER_MAIL = "USERMAIL1";
    public static final String USER_PASSWORD = "USERPASSWORD1";
    public static final int USER_CREDITS = 124;

    @Test
    public void saveNotExistsUserIdShouldInsert() {

        // create object and set atributes
        UserDto userDto = new UserDto();
        userDto.setUsername(USER_NAME);
        userDto.setMail(USER_MAIL);
        userDto.setPswd(USER_PASSWORD);
        userDto.setCredits(USER_CREDITS);

        ArgumentCaptor<UserEntity> user = ArgumentCaptor.forClass(UserEntity.class);

        // save object
        userService.save(null, userDto);

        // verify object has been saved
        verify(userRepository).save(user.capture());

        // check constants declared above are equal to the attributes of the object
        // saved
        assertEquals(USER_NAME, user.getValue().getUsername());
        assertEquals(USER_MAIL, user.getValue().getMail());
        assertEquals(USER_PASSWORD, user.getValue().getPswd());
        assertEquals(USER_CREDITS, user.getValue().getCredits());

    }

    public static final Long EXISTS_USER_ID = 1L;

    @Test
    public void saveExistsUserIdShouldUpdate() {

        UserDto userDto = new UserDto();
        userDto.setUsername(USER_NAME);

        UserEntity user = mock(UserEntity.class);
        when(userRepository.findById(EXISTS_USER_ID)).thenReturn(Optional.of(user));

        userService.save(EXISTS_USER_ID, userDto);

        verify(userRepository).save(user);
    }

    @Test
    public void deleteExistsUserIdShouldDelete() throws Exception {

        UserEntity user = mock(UserEntity.class);
        when(userRepository.findById(EXISTS_USER_ID)).thenReturn(Optional.of(user));

        userService.delete(EXISTS_USER_ID);

        verify(userRepository).deleteById(EXISTS_USER_ID);
    }
}
