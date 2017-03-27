package pl.wykop.domain.annotations.validators;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.wykop.domain.annotations.CategoryName;
import pl.wykop.util.ConfigSource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by mariusz on 27.03.17.
 */
@Data
@Component
public class CategoryNameValidator implements ConstraintValidator<CategoryName, String> {

    private final Logger logger = LoggerFactory.getLogger(CategoryNameValidator.class);
    private final ConfigSource configSource;
    private String pattern;
    private int max;
    private int min;

    @Override
    public void initialize(CategoryName categoryName) {
        min = configSource.getEnv("category.name.length.min", Integer.class);
        max = configSource.getEnv("category.name.length.max", Integer.class);
        pattern = configSource.getEnv("category.name.pattern");
        if (min > max) {
            logger.error("Invalid config parameters! Min length > Max Length ( {} , {} )", min, max);
        } else if (min == max) {
            logger.warn("Min and max length of category name is equal! Allowed only names with length of {}", min);
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        int length = value.length();
        return min <= length && length <= max && Pattern.matches(pattern, value);

    }
}
