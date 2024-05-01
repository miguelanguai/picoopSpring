package project.picoop.auth;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import project.picoop.product.model.ProductEntity;
import project.picoop.user.model.UserEntity;

/**
 * Every time an entity is added to the application, it needs to be here as an
 * attribute
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String name;
    private String email;
    private String role;
    private String password;
    private List<ProductEntity> products;
    private UserEntity user;
}
