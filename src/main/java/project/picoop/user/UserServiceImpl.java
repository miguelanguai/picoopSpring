package project.picoop.user;

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

}
