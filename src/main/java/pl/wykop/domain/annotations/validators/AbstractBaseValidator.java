package pl.wykop.domain.annotations.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by mariusz on 19.03.17.
 */
@Component
public abstract class AbstractBaseValidator {

    @Autowired
    private Environment environment;
    @Autowired
    private MessageSource messageSource;

    public String getEnv(String code) {
        return getEnv(code, String.class);
    }

    public <T> T getEnv(String code, Class<T> type) {
        return environment.getProperty(code, type);
    }

    protected String getMessage(String code) {
        return getMessage(code, new Objects[0]);
    }

    protected String getMessage(String code, Objects[] objects) {
        return getMessage(code, objects, LocaleContextHolder.getLocale());
    }

    protected String getMessage(String code, Objects[] objects, Locale locale) {
        return messageSource.getMessage(code, objects, locale);
    }

}
