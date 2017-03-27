package pl.wykop.domain.annotations.validators;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wykop.domain.annotations.CellImageFileName;
import pl.wykop.util.ConfigSource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by mariusz on 25.03.17.
 */
@Data
public class CellImageFileNameValidator implements ConstraintValidator<CellImageFileName, String> {

    private final Logger logger = LoggerFactory.getLogger(CellImageFileNameValidator.class);
    private final ConfigSource configSource;
    private int max;
    private int min;
    private List<String> allowedExtensions;
    private String pattern;

    @Override
    public void initialize(CellImageFileName cellImageFileName) {
        min = configSource.getEnv("image.name.length.min", Integer.class);
        max = configSource.getEnv("image.name.length.max", Integer.class);
        allowedExtensions = getAllowedFileExtensions();
        pattern = configSource.getEnv("image.name.pattern");
        logger.debug("Min: {}, Max: {}, Allowed extensions: {}, pattern: {}", min, max, allowedExtensions.stream().reduce("", (prev, next) -> prev += next), pattern);
        if (min > max) {
            logger.error("Invalid config parameters! Min length > Max Length ( {} , {} )", min, max);
        } else if (min == max) {
            logger.warn("Min and max length is equal! Allowed only strings with length of {}", min);
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = min <= value.length() && value.length() <= max && Pattern.matches(pattern, value) && endsWithAllowedExtension(value);
        logger.debug("Value: \"{}\" is {}.", value, valid ? "valid" : "invalid");
        return valid;
    }

    private List<String> getAllowedFileExtensions() {
        return Arrays.asList(configSource.getEnv("image.name.extensions").split(","));
    }

    private boolean endsWithAllowedExtension(String value) {
        return allowedExtensions.stream().anyMatch(value::endsWith);
    }
}
