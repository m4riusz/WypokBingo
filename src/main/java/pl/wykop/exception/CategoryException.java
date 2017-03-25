package pl.wykop.exception;

/**
 * Created by mariusz on 25.03.17.
 */
public class CategoryException extends BingoException {
    public CategoryException() {
    }

    public CategoryException(String message) {
        super(message);
    }

    public CategoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
