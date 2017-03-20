package pl.wykop.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by mariusz on 20.03.17.
 */
@Component
public interface LocaleMessageSource {

    default String getMessage(String code) {
        return getMessage(code, new Objects[0]);
    }

    default String getMessage(String code, Objects[] objects) {
        return getMessage(code, objects, LocaleContextHolder.getLocale());
    }

    String getMessage(String code, Objects[] objects, Locale locale);

}
