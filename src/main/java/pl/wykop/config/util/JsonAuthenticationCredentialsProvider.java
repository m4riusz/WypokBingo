package pl.wykop.config.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.wykop.config.authentication.AuthenticationRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by mariusz on 07.03.17.
 */
@Component
@AllArgsConstructor
public class JsonAuthenticationCredentialsProvider implements AuthenticationCredentialsProvider {

    private final ObjectMapper objectMapper;

    @Override
    public AuthenticationRequest getCredentials(HttpServletRequest req) throws IOException {
        return objectMapper.readValue(req.getReader(), AuthenticationRequest.class);
    }
}
