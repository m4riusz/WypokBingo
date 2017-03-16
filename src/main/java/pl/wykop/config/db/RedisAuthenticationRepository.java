package pl.wykop.config.db;

import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.wykop.domain.User;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by mariusz on 15.03.17.
 */
@Data
@Component
public class RedisAuthenticationRepository implements AuthenticationRepository {

    public static final int NUMBER_OF_UNITS = 24;
    public static final TimeUnit EXPIRE_TIME_UNIT = TimeUnit.HOURS;
    private final RedisTemplate<String, User> redisTemplate;

    @Override
    public String login(Authentication authentication) {
        ValueOperations<String, User> stringUserValueOperations = redisTemplate.opsForValue();

        User principal = (User) authentication.getPrincipal();
        do {
            String token = UUID.randomUUID().toString();
            User userWithGeneratedToken = stringUserValueOperations.get(token);
            if (tokenBelongsToNobody(userWithGeneratedToken)) {
                stringUserValueOperations.set(token, principal, NUMBER_OF_UNITS, EXPIRE_TIME_UNIT);
                return token;
            } else if (tokenBelongsToValidUser(principal, userWithGeneratedToken)) {
                redisTemplate.expire(token, NUMBER_OF_UNITS, EXPIRE_TIME_UNIT);
                return token;
            }

        } while (true);
    }

    @Override
    public void logout(String token) {
        ValueOperations<String, User> stringUserValueOperations = redisTemplate.opsForValue();
        if (token != null) {
            stringUserValueOperations.getOperations().delete(token);
        }
    }

    @Override
    public User getUser(String token) {
        ValueOperations<String, User> stringUserValueOperations = redisTemplate.opsForValue();
        return stringUserValueOperations.get(token);
    }

    private boolean tokenBelongsToValidUser(User principal, User userWithGeneratedToken) {
        return userWithGeneratedToken.equals(principal);
    }

    private boolean tokenBelongsToNobody(User userWithGeneratedToken) {
        return userWithGeneratedToken == null;
    }
}
