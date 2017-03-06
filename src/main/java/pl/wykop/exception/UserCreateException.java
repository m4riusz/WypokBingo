package pl.wykop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mariusz on 06.03.17.
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserCreateException extends UserException {
    public UserCreateException() {
    }

    public UserCreateException(String message) {
        super(message);
    }

    public UserCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
