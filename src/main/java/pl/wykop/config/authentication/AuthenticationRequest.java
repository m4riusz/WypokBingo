package pl.wykop.config.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by mariusz on 07.03.17.
 */
@Data
@AllArgsConstructor
public class AuthenticationRequest {
    private String username;
    private String password;
}
