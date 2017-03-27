package pl.wykop.domain.annotations;

import pl.wykop.domain.annotations.validators.CategoryNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mariusz on 27.03.17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CategoryNameValidator.class)
public @interface CategoryName {

    String message() default "{validator.category.name}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
