package common.exceptions;

public class SameStorageUnitException extends RuntimeException {

    public SameStorageUnitException() {
        super();
    }

    public SameStorageUnitException(String message) {
        super(message);
    }
}
