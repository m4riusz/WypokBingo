package pl.wykop.domain.annotations;

import pl.wykop.domain.annotations.validators.BoardNameValidatorAbstract;

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
@Constraint(validatedBy = BoardNameValidatorAbstract.class)
public @interface BoardName {

    String message() default "Invalid board name format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
