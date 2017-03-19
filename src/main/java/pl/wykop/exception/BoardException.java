package pl.wykop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mariusz on 17.03.17.
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class BoardException extends BingoException {
    public BoardException() {
    }

    public BoardException(String message) {
        super(message);
    }

    public BoardException(String message, Throwable cause) {
        super(message, cause);
    }
}
