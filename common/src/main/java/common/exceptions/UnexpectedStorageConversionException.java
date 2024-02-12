package common.exceptions;

public class UnexpectedStorageConversionException extends RuntimeException {

    public UnexpectedStorageConversionException() {
        super();
    }

    public UnexpectedStorageConversionException(String message) {
        super(message);
    }
}
