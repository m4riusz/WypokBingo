package pl.wykop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mariusz on 21.03.17.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BoardNotFoundException extends BoardException {
    public BoardNotFoundException() {
    }

    public BoardNotFoundException(String message) {
        super(message);
    }

    public BoardNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
