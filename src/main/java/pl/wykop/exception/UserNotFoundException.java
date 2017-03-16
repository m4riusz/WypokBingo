package pl.wykop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mariusz on 16.03.17.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends UserException {

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
