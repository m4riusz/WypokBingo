package pl.wykop.util;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by mariusz on 20.03.17.
 */

@Component
@AllArgsConstructor
public class PropertyLocaleMessageSource implements LocaleMessageSource {

    private final MessageSource messageSource;

    @Override
    public String getMessage(String code, Objects[] objects, Locale locale) {
        return messageSource.getMessage(code, objects, locale);
    }
}
