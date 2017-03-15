package pl.wykop.service;

import lombok.Data;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by mariusz on 15.03.17.
 */
@Data
@Service
public abstract class AbstractService {

    private final MessageSource messageSource;

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
