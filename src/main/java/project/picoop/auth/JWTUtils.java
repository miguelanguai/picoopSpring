package project.picoop.auth;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JWTUtils {

    private SecretKey Key;
    private static final long EXPIRATION_TIME = 86400000; // 24hours or 86400000 milisecs

    /**
     * generates a secrete string and a key
     */
    public JWTUtils() {
        String secreteString = "843567893696976453275974432697R634976R738467TR678T34865R6834R8763T478378637664538745673865783678548735687R3";
        byte[] keyBytes = Base64.getDecoder().decode(secreteString.getBytes(StandardCharsets.UTF_8));
        this.Key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    /**
     * generates a token
     * 
     * @param userDetails
     * @return {@link Jwts}
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(Key).compact();
        // cambiado subject, issuedAt y expiration por lo mismo con set delante
    }

    /**
     * generates a refresh token
     * 
     * @param claims
     * @param userDetails
     * @return {@link Jwts}
     */
    public String generateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(Key).compact();
        // lo mismo que arriba pero tambien con claims
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    /*
     * private <T> T extractClaims(String token, Function<Claims, T>
     * claimsTFunction) { return
     * claimsTFunction.apply(Jwts.parser().verifyWith(Key).build().parseSignedClaims
     * (token).getPayload()); } sustituido por el de debajo
     */

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(Jwts.parser().setSigningKey(Key).parseClaimsJws(token).getBody());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

}
