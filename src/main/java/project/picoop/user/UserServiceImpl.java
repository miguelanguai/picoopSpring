package project.picoop.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import project.picoop.user.model.UserEntity;

/**
 * @author mguaita
 */
@Service
public class UserServiceImpl implements UserService {

    UserEntity userInSession; // esto esta asi para que los metodos de debajo no se rompan

    /**
     * {@inheritDoc}
     */
    public void sumCreditsToUser(int creditsToSum) {
        userInSession.setCredits(userInSession.getCredits() + creditsToSum);
    }

    /**
     * {@inheritDoc}
     */
    public void substractCreditsToUser(int creditsToSubstract) {
        if (isUserAbleToPay(creditsToSubstract)) {
            userInSession.setCredits(userInSession.getCredits() - creditsToSubstract);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isUserAbleToPay(int creditsWantedToBePaid) {
        if (userInSession.getCredits() < creditsWantedToBePaid) {
            return false;
        }
        return true;
    }

    // GESTION
    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Object> userAlone() {
        return ResponseEntity.ok("USers alone can access this ApI only");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Object> bothAdminAndUserApi() {
        return ResponseEntity.ok("Both Admin and Users Can  access the api");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication); // get all details(name,email,password,roles e.t.c) of the user
        System.out.println(authentication.getDetails()); // get remote ip
        System.out.println(authentication.getName()); // returns the email because the email is the unique identifier
        return authentication.getName(); // returns the email
    }

}
