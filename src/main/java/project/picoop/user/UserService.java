package project.picoop.user;

/**
 * @author mguaitav
 */
public interface UserService {

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

}
