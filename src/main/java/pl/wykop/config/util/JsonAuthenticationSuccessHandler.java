package pl.wykop.config.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.wykop.config.authentication.AuthenticationResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mariusz on 07.03.17.
 */
@Component
@AllArgsConstructor
public class JsonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final String APPLICATION_JSON = "application/json";
    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletResponse res, String token) throws IOException {
        AuthenticationResult authenticationResult = new AuthenticationResult();
        authenticationResult.setToken(token);
        authenticationResult.setSuccess(true);
        String jsonBody = objectMapper.writeValueAsString(authenticationResult);
        res.getWriter().write(jsonBody);
        res.setContentType(APPLICATION_JSON);
        res.setStatus(HttpServletResponse.SC_OK);
    }
}
