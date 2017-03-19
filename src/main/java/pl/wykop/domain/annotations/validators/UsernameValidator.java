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
public class UsernameValidator extends AbstractBaseValidator implements ConstraintValidator<Username, String> {

    private static final String USERNAME_FORMAT = "[a-zA-z0-9_]{4,20}";

    @Override
    public void initialize(Username username) {

    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (Pattern.matches(USERNAME_FORMAT, username)) {
            return true;
        }
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(getMessage("validator.user.username")).addConstraintViolation();
        return false;
    }
}
