package pl.wykop.util;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by mariusz on 20.03.17.
 */
@Component
@AllArgsConstructor
@PropertySource(value = "classpath:config.properties")
public class PropertyConfigSource implements ConfigSource {

    private final Environment environment;

    @Override
    public <T> T getEnv(String code, Class<T> type) {
        return environment.getProperty(code, type);
    }
}
