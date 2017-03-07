package pl.wykop.config.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pl.wykop.config.authentication.AuthenticationResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mariusz on 07.03.17.
 */
@Component
@AllArgsConstructor
public class JsonAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final String APPLICATION_JSON = "application/json";
    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletResponse res, AuthenticationException authenticationException) throws IOException {
        AuthenticationResult authenticationResult = new AuthenticationResult();
        authenticationResult.setMessage(authenticationException.getMessage());
        authenticationResult.setSuccess(false);
        String jsonBody = objectMapper.writeValueAsString(authenticationResult);
        res.getWriter().write(jsonBody);
        res.setContentType(APPLICATION_JSON);
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
