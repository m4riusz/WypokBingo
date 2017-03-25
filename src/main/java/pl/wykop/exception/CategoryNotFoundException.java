package pl.wykop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mariusz on 25.03.17.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends CategoryException {
    public CategoryNotFoundException() {
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
