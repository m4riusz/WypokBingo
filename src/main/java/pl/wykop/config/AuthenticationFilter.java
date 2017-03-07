package pl.wykop.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.wykop.config.authentication.AuthenticationRequest;
import pl.wykop.config.util.AuthenticationFailureHandler;
import pl.wykop.config.util.AuthenticationSuccessHandler;
import pl.wykop.config.util.AuthenticationCredentialsProvider;
import pl.wykop.domain.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by mariusz on 07.03.17.
 */
@Component

public class AuthenticationFilter extends OncePerRequestFilter {
    public static final String LOGIN_URL = "/api/login";
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
    private RedisTemplate<String, User> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        if (isLoginRequest(req)) {
            AuthenticationRequest credentials = authenticationCredentialsProvider.getCredentials(req);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
            try {
                Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = addToRedis(authentication);
                authenticationSuccessHandler.handle(res, token);
                logger.debug("Authentication success! Logged as " + authentication.getName());
            } catch (AuthenticationException ex) {
                logger.debug("Authentication failure!", ex);
                authenticationFailureHandler.handle(res, ex);
            }
            return;
        }
        logger.debug("No authentication request.");
        filterChain.doFilter(req, res);

    }

    private String addToRedis(Authentication authentication) {
        ValueOperations<String, User> stringUserValueOperations = redisTemplate.opsForValue();
        UUID uuid = UUID.randomUUID();
        User principal = (User) authentication.getPrincipal();
        stringUserValueOperations.setIfAbsent(uuid.toString(), principal);
        return uuid.toString();
    }

    private boolean isLoginRequest(HttpServletRequest req) {
        return req.getRequestURI().equals(LOGIN_URL);
    }

}
