package project.picoop.user;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import project.picoop.user.model.UserEntity;

/**
 * @author mguaitav
 */
public interface UserService {

    /**
     * find User
     * 
     * @param id the user id
     * @return {@link UserEntity}
     */
    public Optional<UserEntity> findUserById(Integer id);

    /**
     * find User
     * 
     * @param string the username
     * @return {@link UserEntity}
     */
    public UserEntity findUserByUsername(String username);

    // CREDITS
    /**
     * sum application credits to the user
     * 
     * @param credits
     */
    public void sumCreditsToUser(int credits);

    /**
     * rest application credits to the user
     * 
     * @param credits
     */
    public void substractCreditsToUser(int credits);

    /**
     * checks if user has enough credits to pay for an image
     * 
     * @param creditsWantedToBePaid
     * @return boolean
     */
    public boolean isUserAbleToPay(int creditsWantedToBePaid);

    // GESTION

    /**
     * method to test only users are able to access this url in the api (not admin,
     * not no-registered clients)
     * 
     * @return
     */
    ResponseEntity<Object> userAlone();

    /**
     * method to test only registered clients are able to access this url
     * 
     * @return
     */
    ResponseEntity<Object> bothAdminAndUserApi();

    /**
     * You can use this to get the details(name,email,role,ip, e.t.c) of user
     * accessing the service
     */
    String getCurrentUserEmail();

    /**
     * get current session user
     * 
     * @return
     */
    UserEntity getCurrentUser();

    /**
     * Get credits current user has
     * 
     * @return
     */
    public int getCurrentUserCredits();

    /**
     * Set credits of current user (to test getCurrentUser)
     * 
     * @param credits
     */
    public void setCurrentUserCredits(int credits);

}
