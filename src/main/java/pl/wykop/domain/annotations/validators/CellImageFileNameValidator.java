package pl.wykop.domain.annotations.validators;

import lombok.Data;
import org.springframework.stereotype.Component;
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
@Component
public class CellImageFileNameValidator implements ConstraintValidator<CellImageFileName, String> {

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
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        int length = value.length();
        return min <= length && length <= max && Pattern.matches(pattern, value) && endsWithAllowedExtension(value);
    }

    private List<String> getAllowedFileExtensions() {
        return Arrays.asList(configSource.getEnv("image.name.extensions").split(","));
    }

    private boolean endsWithAllowedExtension(String value) {
        return allowedExtensions.stream().anyMatch(value::endsWith);
    }
}
