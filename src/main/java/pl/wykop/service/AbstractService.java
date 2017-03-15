package pl.wykop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by mariusz on 15.03.17.
 */

public abstract class AbstractService {
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code) {
        return getMessage(code, new Objects[0]);
    }

    public String getMessage(String code, Objects[] objects) {
        return getMessage(code, objects, LocaleContextHolder.getLocale());
    }

    public String getMessage(String code, Objects[] objects, Locale locale) {
        return messageSource.getMessage(code, objects, locale);
    }
}
