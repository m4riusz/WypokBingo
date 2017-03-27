package pl.wykop.domain.annotations.validators;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wykop.domain.annotations.BoardName;
import pl.wykop.util.ConfigSource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by mariusz on 19.03.17.
 */
@Data
public class BoardNameValidator implements ConstraintValidator<BoardName, String> {

    private final Logger logger = LoggerFactory.getLogger(BoardNameValidator.class);
    private final ConfigSource configSource;
    private String pattern;
    private int max;
    private int min;

    @Override
    public void initialize(BoardName boardName) {
        pattern = configSource.getEnv("board.name.pattern");
        min = configSource.getEnv("board.name.length.min", Integer.class);
        max = configSource.getEnv("board.name.length.max", Integer.class);
        logger.debug("Min: {}, Max: {}, Pattern : \"{}\"", min, max, pattern);
        if (min > max) {
            logger.error("Invalid config parameters! Min length > Max Length ( {} , {} )", min, max);
        } else if (min == max) {
            logger.warn("Min and max length is equal! Allowed only strings with length of {}", min);
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = min <= value.length() && value.length() <= max && Pattern.matches(pattern, value);
        logger.debug("Value: \"{}\" is {}.", value, valid ? "valid" : "invalid");
        return valid;
    }
}
