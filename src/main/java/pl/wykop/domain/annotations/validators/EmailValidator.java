package pl.wykop.domain.annotations.validators;

import org.springframework.stereotype.Component;
import pl.wykop.domain.annotations.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by mariusz on 19.03.17.
 */
@Component
public class EmailValidator implements ConstraintValidator<Email, String> {

    private String pattern;

    @Override
    public void initialize(Email email) {
        pattern = email.pattern();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(pattern, email);
    }

}
