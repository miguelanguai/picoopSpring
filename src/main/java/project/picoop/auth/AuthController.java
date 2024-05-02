package project.picoop.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * it calls {@link AuthService.signUp}
     * 
     * @param signUpRequest
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<ReqRes> signUp(@RequestBody ReqRes signUpRequest) {
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }

    /**
     * it calls {@link AuthService.signIn}
     * 
     * @param signUpRequest
     * @return
     */
    @PostMapping("/signin")
    public ResponseEntity<ReqRes> signIn(@RequestBody ReqRes signInRequest) {
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }

    /**
     * It refreshes token
     * 
     * @param signUpRequest
     * @return
     */
    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes refreshTokenRequest) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }

    /**
     * It ends the session for the user
     * 
     * @param signOutRequest
     * @return
     */
    @PostMapping("/signout")
    public ResponseEntity<ReqRes> signOut(@RequestBody ReqRes signOutRequest) {
        return ResponseEntity.ok(authService.signOut(signOutRequest));
    }
}