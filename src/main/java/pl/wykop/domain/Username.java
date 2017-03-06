package pl.wykop.domain;

import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mariusz on 06.03.17.
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Pattern(regexp = "^[a-zA-z0-9_]{4,20}$", message = "Wrong username format!")
public @interface Username {
}