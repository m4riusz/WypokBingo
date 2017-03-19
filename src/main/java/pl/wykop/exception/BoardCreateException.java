package pl.wykop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mariusz on 17.03.17.
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class BoardCreateException extends BoardException {
    public BoardCreateException() {
    }

    public BoardCreateException(String message) {
        super(message);
    }

    public BoardCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
