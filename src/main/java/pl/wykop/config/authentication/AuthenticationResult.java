package pl.wykop.config.authentication;

import lombok.Data;

/**
 * Created by mariusz on 07.03.17.
 */
@Data
public class AuthenticationResult {

    private boolean success;
    private String message;
    private String token;
}
