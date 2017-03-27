package pl.wykop.domain.annotations;

import pl.wykop.domain.annotations.validators.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mariusz on 06.03.17.
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {

    String message() default "{validator.user.password}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
