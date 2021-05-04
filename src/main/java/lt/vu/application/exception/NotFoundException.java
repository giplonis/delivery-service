package lt.vu.application.exception;

public abstract class NotFoundException extends Exception {

    public NotFoundException(String message) {
        super(message);
    }
}
