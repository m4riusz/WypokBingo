package pl.wykop.domain.annotations;

import pl.wykop.domain.annotations.validators.BoardDimensionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mariusz on 20.03.17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = BoardDimensionValidator.class)
public @interface BoardDimension {

    short min() default 3;

    short max() default 9;

    String message() default "{validator.board.name}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
