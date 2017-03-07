package pl.wykop.config.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mariusz on 07.03.17.
 */
@Component
public interface AuthenticationSuccessHandler {

    void handle(HttpServletResponse httpServletResponse, String token) throws IOException;
}
