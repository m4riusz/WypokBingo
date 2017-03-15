package pl.wykop.config.db;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Created by mariusz on 15.03.17.
 */
@Component
public interface AuthenticationRepository {

    String login(Authentication authentication);

    void logout(String token);
}
