package common.exceptions;

public class InvalidTimeUnitException extends RuntimeException {

    public InvalidTimeUnitException() {
        super();
    }

    public InvalidTimeUnitException(String message) {
        super(message);
    }
}
