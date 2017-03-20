package pl.wykop.util;

import org.springframework.stereotype.Component;

/**
 * Created by mariusz on 20.03.17.
 */
@Component
public interface ConfigSource {

    default String getEnv(String code) {
        return getEnv(code, String.class);
    }

    <T> T getEnv(String code, Class<T> type);

}
