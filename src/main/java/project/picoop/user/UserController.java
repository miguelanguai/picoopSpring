package project.picoop.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * method to test only users are able to access this url in the api (not admin,
     * not no-registered clients)
     * 
     * @return
     */
    @GetMapping("/user/alone")
    public ResponseEntity<Object> userAlone() {
        return userService.userAlone();
    }

    /**
     * method to test only registered clients are able to access this url
     * 
     * @return
     */
    @GetMapping("/adminuser/both")
    public ResponseEntity<Object> bothAdminaAndUsersApi() {
        return userService.bothAdminAndUserApi();
    }

    /**
     * You can use this to get the details(name,email,role,ip, e.t.c) of user
     * accessing the service
     */
    @GetMapping("/public/email")
    public String getCurrentUserEmail() {
        return userService.getCurrentUserEmail();
    }

    /**
     * method to check how many credits user has
     */
    @GetMapping("/user/credits")
    public int getCurrentUserCredits() {
        return userService.getCurrentUserCredits();
    }

    /**
     * method to put credits into a new user (to test the getCredits)
     */
    @RequestMapping(path = "/user/credits/{credits}", method = RequestMethod.PUT)
    public void setCurrentUserCredits(@PathVariable(name = "credits", required = true) int credits) {
        this.userService.setCurrentUserCredits(credits);
    }
}
