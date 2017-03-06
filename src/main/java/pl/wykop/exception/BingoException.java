package pl.wykop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mariusz on 06.03.17.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class BingoException extends Exception {
    public BingoException() {
    }

    public BingoException(String message) {
        super(message);
    }

    public BingoException(String message, Throwable cause) {
        super(message, cause);
    }
}
