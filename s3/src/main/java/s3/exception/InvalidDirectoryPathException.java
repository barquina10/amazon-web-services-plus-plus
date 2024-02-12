package s3.exception;

public class InvalidDirectoryPathException extends Exception {

    public InvalidDirectoryPathException() {
        super();
    }

    public InvalidDirectoryPathException(String message) {
        super(message);
    }
}
