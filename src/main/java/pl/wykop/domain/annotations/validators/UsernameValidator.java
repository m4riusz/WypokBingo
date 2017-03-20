package pl.wykop.domain.annotations.validators;

import org.springframework.stereotype.Component;
import pl.wykop.domain.annotations.Username;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by mariusz on 19.03.17.
 */
@Component
public class UsernameValidator implements ConstraintValidator<Username, String> {

    private String pattern;

    @Override
    public void initialize(Username username) {
        pattern = username.pattern();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(pattern, username);

    }
}
