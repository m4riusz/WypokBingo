package pl.wykop.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.wykop.config.authentication.AuthenticationRequest;
import pl.wykop.config.db.AuthenticationRepository;
import pl.wykop.config.util.AuthenticationCredentialsProvider;
import pl.wykop.config.util.AuthenticationFailureHandler;
import pl.wykop.config.util.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mariusz on 07.03.17.
 */
@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    public static final String TOKEN_HEADER = "Auth-Token";
    private static final String POST = "POST";
    private static final String DELETE = "DELETE";
    private Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthenticationCredentialsProvider authenticationCredentialsProvider;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        if (isLoginRequest(req)) {
            logger.debug("Authentication login request!");
            AuthenticationRequest credentials = authenticationCredentialsProvider.getCredentials(req);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
            try {
                Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = authenticationRepository.login(authentication);
                authenticationSuccessHandler.handle(res, token);
                logger.debug("Authentication login success! Logged as " + authentication.getName());
            } catch (AuthenticationException ex) {
                logger.debug("Authentication login failure!", ex);
                authenticationFailureHandler.handle(res, ex);
            }
            return;
        } else if (isLogoutRequest(req)) {
            String token = getTokenHeaderValue(req);
            logger.debug("Authentication logout request! Token: " + token);
            authenticationRepository.logout(token);
            return;
        }
        logger.debug("No authentication request.");
        filterChain.doFilter(req, res);
    }

    private boolean isLogoutRequest(HttpServletRequest req) {
        return req.getRequestURI().equals(Route.AUTH_URL) && req.getMethod().equalsIgnoreCase(DELETE);
    }


    private boolean isLoginRequest(HttpServletRequest req) {
        return req.getRequestURI().equals(Route.AUTH_URL) && req.getMethod().equalsIgnoreCase(POST);
    }

    private String getTokenHeaderValue(HttpServletRequest req) {
        return req.getHeader(TOKEN_HEADER);
    }
}
