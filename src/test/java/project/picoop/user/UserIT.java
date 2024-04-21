package project.picoop.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import project.picoop.user.model.UserDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/user";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<List<UserDto>> responseType = new ParameterizedTypeReference<List<UserDto>>() {
    };

    @Test
    public void findAllShouldReturnAllUsers() {

        ResponseEntity<List<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET,
                null, responseType);

        assertNotNull(response);
        assertEquals(3, response.getBody().size()); // first parameter is number of inserts in
                                                    // src/main/resources/data.sql
    }

    public static final Long NEW_USER_ID = 4L;
    public static final String NEW_USER_NAME = "newUser";
    public static final String NEW_USER_MAIL = "newUserMail";
    public static final String NEW_USER_PASSWORD = "newUserPswd";
    public static final int NEW_USER_CREDITS = 457;

    @Test
    public void saveWithoutIdShouldCreateNewUser() {

        UserDto dto = new UserDto();
        dto.setUsername(NEW_USER_NAME);
        dto.setMail(NEW_USER_MAIL);
        dto.setPswd(NEW_USER_PASSWORD);
        dto.setCredits(NEW_USER_CREDITS);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        ResponseEntity<List<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET,
                null, responseType);
        assertNotNull(response); // checks it is not null
        assertEquals(4, response.getBody().size()); // checks it is equal to 4 (3 + the new one created)

        UserDto userSearch = response.getBody().stream().filter(item -> item.getId().equals(NEW_USER_ID)).findFirst()
                .orElse(null);
        assertNotNull(userSearch);
        assertEquals(NEW_USER_NAME, userSearch.getUsername());
        assertEquals(NEW_USER_MAIL, userSearch.getMail());
        assertEquals(NEW_USER_PASSWORD, userSearch.getPswd());
        assertEquals(NEW_USER_CREDITS, userSearch.getCredits());
    }

    public static final Long MODIFY_USER_ID = 3L;

    @Test
    public void modifyWithExistIdShouldModifyUser() {

        UserDto dto = new UserDto();
        dto.setUsername(NEW_USER_NAME);
        dto.setMail(NEW_USER_MAIL);
        dto.setPswd(NEW_USER_PASSWORD);
        dto.setCredits(NEW_USER_CREDITS);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + MODIFY_USER_ID, HttpMethod.PUT,
                new HttpEntity<>(dto), Void.class);

        ResponseEntity<List<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET,
                null, responseType);
        assertNotNull(response);
        assertEquals(3, response.getBody().size());

        UserDto userSearch = response.getBody().stream().filter(item -> item.getId().equals(MODIFY_USER_ID)).findFirst()
                .orElse(null);
        assertNotNull(userSearch);
        assertEquals(NEW_USER_NAME, userSearch.getUsername());
        assertEquals(NEW_USER_MAIL, userSearch.getMail());
        assertEquals(NEW_USER_PASSWORD, userSearch.getPswd());
        assertEquals(NEW_USER_CREDITS, userSearch.getCredits());
    }

    @Test
    public void modifyWithNotExistIdShouldInternalError() {

        UserDto dto = new UserDto();
        dto.setUsername(NEW_USER_NAME);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + NEW_USER_ID,
                HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    public static final Long DELETE_USER_ID = 2L;

    @Test
    public void deleteWithExistsIdShouldDeleteUser() {

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + DELETE_USER_ID, HttpMethod.DELETE, null,
                Void.class);

        ResponseEntity<List<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET,
                null, responseType);
        assertNotNull(response);
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void deleteWithNotExistsIdShouldInternalError() {

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + NEW_USER_ID,
                HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}
