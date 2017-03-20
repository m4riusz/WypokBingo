package pl.wykop.domain.annotations;

import pl.wykop.domain.annotations.validators.BoardNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mariusz on 17.03.17.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Constraint(validatedBy = BoardNameValidator.class)
public @interface BoardName {

    String pattern() default "^[a-zA-z0-9_-]{3,20}$";

    String message() default "{validator.board.name}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
