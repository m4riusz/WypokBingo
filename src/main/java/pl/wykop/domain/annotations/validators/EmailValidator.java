package pl.wykop.domain.annotations.validators;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wykop.domain.annotations.Email;
import pl.wykop.util.ConfigSource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by mariusz on 19.03.17.
 */
@Data
public class EmailValidator implements ConstraintValidator<Email, String> {

    private Logger logger = LoggerFactory.getLogger(EmailValidator.class);
    private final ConfigSource configSource;
    private String pattern;
    private int min;
    private int max;

    @Override
    public void initialize(Email email) {
        min = configSource.getEnv("email.length.min", Integer.class);
        max = configSource.getEnv("email.length.max", Integer.class);
        pattern = configSource.getEnv("email.pattern");
        logger.debug("Min: {}, Max: {}, Pattern: {}", min, max, pattern);
        if (min > max) {
            logger.error("Invalid config parameters! Min length > Max Length ( {} , {} )", min, max);
        } else if (min == max) {
            logger.warn("Min and max length is equal! Allowed only strings with length of {}", min);
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = Pattern.matches(pattern, value);
        logger.debug("Value: \"{}\" is {}.", value, valid ? "valid" : "invalid");
        return valid;
    }

}
