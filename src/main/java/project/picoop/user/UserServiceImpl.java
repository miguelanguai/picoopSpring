package project.picoop.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserRepository userRepository;

    UserEntity userEntity;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserEntity> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity findUserByUsername(String username) {
        UserEntity user = userRepository.findByEmail(username).orElse(null);
        return user;
    }

    /**
     * {@inheritDoc}
     */
    public void sumCreditsToUser(int creditsToSum) {
        this.getCurrentUser().setCredits(userEntity.getCredits() + creditsToSum);
        // me falta guardar los creditos del usuario (o editar el usuario entero)
    }

    /**
     * {@inheritDoc}
     */
    public void substractCreditsToUser(int creditsToSubstract) {
        if (isUserAbleToPay(creditsToSubstract)) {
            this.getCurrentUser().setCredits(userEntity.getCredits() - creditsToSubstract);
            // me falta guardar los creditos del usuario (o editar el usuario entero)
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isUserAbleToPay(int creditsWantedToBePaid) {
        if (this.getCurrentUser().getCredits() < creditsWantedToBePaid) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepository.findByEmail(authentication.getName()).orElse(null);
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentUserCredits() {
        return this.getCurrentUser().getCredits();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentUserCredits(int credits) {
        userRepository.setCurrentUserCredits(this.getCurrentUser().getId(), credits);

    }

}
