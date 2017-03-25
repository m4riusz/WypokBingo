package pl.wykop.domain.annotations.validators;

import org.springframework.stereotype.Component;
import pl.wykop.domain.annotations.BoardDimension;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by mariusz on 20.03.17.
 */
@Component
public class BoardDimensionValidator implements ConstraintValidator<BoardDimension, Short> {

    private short min;
    private short max;

    @Override
    public void initialize(BoardDimension boardDimension) {
        min = boardDimension.min();
        max = boardDimension.max();
    }

    @Override
    public boolean isValid(Short value, ConstraintValidatorContext constraintValidatorContext) {
        return min <= value && value <= max && value % 2 == 1;
    }
}
