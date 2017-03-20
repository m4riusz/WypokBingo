package pl.wykop.domain.annotations.validators;

import org.springframework.stereotype.Component;
import pl.wykop.domain.annotations.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by mariusz on 19.03.17.
 */
@Component
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private String pattern;

    @Override
    public void initialize(Password password) {
        pattern = password.pattern();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(pattern, password);

    }
}
