package pl.wykop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by mariusz on 15.03.17.
 */

@PropertySource(value = "classpath:config.properties")
public abstract class AbstractService {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private Environment environment;

    protected String getMessage(String code) {
        return getMessage(code, new Objects[0]);
    }

    protected String getMessage(String code, Objects[] objects) {
        return getMessage(code, objects, LocaleContextHolder.getLocale());
    }

    protected String getMessage(String code, Objects[] objects, Locale locale) {
        return messageSource.getMessage(code, objects, locale);
    }

    protected String getEnvironment(String code) {
        return getEnvironment(code, String.class);
    }

    protected <T> T getEnvironment(String code, Class<T> type) {
        return environment.getProperty(code, type);
    }
    
}
