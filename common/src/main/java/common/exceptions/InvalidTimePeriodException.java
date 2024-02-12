package common.exceptions;

public class InvalidTimePeriodException extends RuntimeException {

    public InvalidTimePeriodException() {
        super();
    }

    public InvalidTimePeriodException(String message) {
        super(message);
    }
}
