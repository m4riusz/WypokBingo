package pl.wykop.domain.annotations;

import pl.wykop.domain.annotations.validators.CellImageFileNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mariusz on 25.03.17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CellImageFileNameValidator.class)
public @interface CellImageFileName {

    String message() default "{validator.image.file.name}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
