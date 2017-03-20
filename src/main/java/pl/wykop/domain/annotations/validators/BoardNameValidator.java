package pl.wykop.domain.annotations.validators;

import org.springframework.stereotype.Component;
import pl.wykop.domain.annotations.BoardName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by mariusz on 19.03.17.
 */
@Component
public class BoardNameValidator implements ConstraintValidator<BoardName, String> {

    private String pattern;

    @Override
    public void initialize(BoardName boardName) {
        pattern = boardName.pattern();
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(pattern, string);
    }
}
