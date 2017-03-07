package pl.wykop.config.util;

import org.springframework.stereotype.Component;
import pl.wykop.config.authentication.AuthenticationRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by mariusz on 07.03.17.
 */
@Component
public interface AuthenticationCredentialsProvider {

    AuthenticationRequest getCredentials(HttpServletRequest req) throws IOException;
}
