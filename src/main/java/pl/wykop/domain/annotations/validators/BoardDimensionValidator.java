package pl.wykop.domain.annotations.validators;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wykop.domain.annotations.BoardDimension;
import pl.wykop.util.ConfigSource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by mariusz on 20.03.17.
 */
@Data
public class BoardDimensionValidator implements ConstraintValidator<BoardDimension, Short> {

    private final Logger logger = LoggerFactory.getLogger(BoardDimensionValidator.class);
    private final ConfigSource configSource;
    private short min;
    private short max;

    @Override
    public void initialize(BoardDimension boardDimension) {
        min = configSource.getEnv("board.dimension.min", Short.class);
        max = configSource.getEnv("board.dimension.max", Short.class);
        logger.debug("Min: {}, Max: {}", min, max);
        if (min > max) {
            logger.error("Invalid config parameters! Min length > Max Length ( {} , {} )", min, max);
        } else if (min == max) {
            logger.warn("Min and max length is equal! Allowed only strings with length of {}", min);
        }
    }

    @Override
    public boolean isValid(Short value, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = min <= value && value <= max && value % 2 == 1;
        logger.debug("Value: \"{}\" is {}.", value, valid ? "valid" : "invalid");
        return valid;
    }
}
