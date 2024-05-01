package project.picoop.user;

import org.springframework.http.ResponseEntity;

/**
 * @author mguaitav
 */
public interface UserService {

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

}
