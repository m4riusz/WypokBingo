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
public class PasswordValidator extends AbstractBaseValidator implements ConstraintValidator<Password, String> {

    private static final String PASSWORD_PATTERN = "[a-zA-z0-9_!@#$%^&*()]{7,20}";

    @Override
    public void initialize(Password password) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (Pattern.matches(PASSWORD_PATTERN, password)) {
            return true;
        }
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(getMessage("validator.user.password")).addConstraintViolation();
        return false;
    }
}
