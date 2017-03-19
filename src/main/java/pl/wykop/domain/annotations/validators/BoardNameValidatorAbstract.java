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
public class BoardNameValidatorAbstract extends AbstractBaseValidator implements ConstraintValidator<BoardName, String> {

    private static final String BOARD_NAME_PATTERN = "^[a-zA-z0-9_-]{3,20}$";

    @Override
    public void initialize(BoardName boardName) {

    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        if (Pattern.matches(BOARD_NAME_PATTERN, string)) {
            return true;
        }
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(getMessage("validator.board.name")).addConstraintViolation();
        return false;
    }
}
