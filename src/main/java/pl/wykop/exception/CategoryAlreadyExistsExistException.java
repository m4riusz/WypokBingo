package pl.wykop.exception;

/**
 * Created by mariusz on 25.03.17.
 */

public class CategoryAlreadyExistsExistException extends CategoryException {
    public CategoryAlreadyExistsExistException() {
    }

    public CategoryAlreadyExistsExistException(String message) {
        super(message);
    }

    public CategoryAlreadyExistsExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
