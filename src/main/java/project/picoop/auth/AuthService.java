package project.picoop.auth;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import project.picoop.user.UserRepository;
import project.picoop.user.model.UserEntity;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * creates a new user with {@link ReqRes} attributes. If it succeeds, it is
     * added to ReqRes object
     * 
     * @param registrationRequest
     * @return {@link ReqRes}
     */
    public ReqRes signUp(ReqRes registrationRequest) {
        ReqRes resp = new ReqRes();
        try {
            UserEntity user = new UserEntity();
            user.setEmail(registrationRequest.getEmail());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setRole(registrationRequest.getRole());
            user.setCredits(0); // credits initialized to 0. After they can be bought
            UserEntity userResult = userRepository.save(user);
            if (userResult != null && userResult.getId() > 0) {
                resp.setUser(userResult);
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            }
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    /**
     * tries to authenticate the user and the password passed by object's parameters
     * in parameter it finds the user by email in db and generates a jwt token and
     * refreshtoken with it
     * 
     * @param {@link ReqRes} signinRequest
     * @return {@link ReqRes} with user data on it
     */
    public ReqRes signIn(ReqRes signinRequest) {
        ReqRes response = new ReqRes();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
            var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow();
            System.out.println("USER IS: " + user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Signed In");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    /**
     * tries to authenticate the user and the password passed by object's parameters
     * in parameter it finds the user by email in db and generates a jwt token and
     * refreshtoken with it
     * 
     * @param {@link ReqRes} refreshTokenReqiest
     * @return {@link ReqRes} with data updated (new token)
     */
    public ReqRes refreshToken(ReqRes refreshTokenReqiest) {
        ReqRes response = new ReqRes();
        String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
        UserEntity users = userRepository.findByEmail(ourEmail).orElseThrow();
        if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenReqiest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }

    /**
     * method to sign out. este lo he creado yo
     * 
     * @param token
     * @return response
     */
    public ReqRes signOut(ReqRes signoutRequest) {
        ReqRes response = new ReqRes();

        try {
            response.setStatusCode(200);
            response.setMessage("Successfully Signed Out");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    // utils
    // Estos tres metodos siguientes los he creado yo
    /**
     * to get user in session email. Este lo he creado yo (explicado por PhegonDev)
     * 
     * @return UserEntity
     */
    public static String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return email;
    }

    /**
     * Set by an AuthenticationManager to indicate the authorities that the
     * principal has been granted
     * 
     * @return the authorities granted to the principal, or an empty collection if
     *         the token has not been authenticated. Never null.
     */
    public static Collection<? extends GrantedAuthority> getCurrentUserAuthority() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities;
    }

    /**
     * Stores additional details about the authentication request. These might be an
     * IP address, certificate serial number etc.
     * 
     * @return Object
     */
    public static Object details() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object details = authentication.getDetails(); // get other details e.t.c
        return details;
    }
}
