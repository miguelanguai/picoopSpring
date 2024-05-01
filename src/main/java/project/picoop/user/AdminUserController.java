package project.picoop.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUserController {

    /**
     * method to test only users are able to access this url in the api (not admin,
     * not no-registered clients)
     * 
     * @return
     */
    @GetMapping("/user/alone")
    public ResponseEntity<Object> userAlone() {
        return ResponseEntity.ok("USers alone can access this ApI only");
    }

    /**
     * method to test only registered clients are able to access this url
     * 
     * @return
     */
    @GetMapping("/adminuser/both")
    public ResponseEntity<Object> bothAdminaAndUsersApi() {
        return ResponseEntity.ok("Both Admin and Users Can  access the api");
    }

    /**
     * You can use this to get the details(name,email,role,ip, e.t.c) of user
     * accessing the service
     */
    @GetMapping("/public/email")
    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication); // get all details(name,email,password,roles e.t.c) of the user
        System.out.println(authentication.getDetails()); // get remote ip
        System.out.println(authentication.getName()); // returns the email because the email is the unique identifier
        return authentication.getName(); // returns the email
    }
}
