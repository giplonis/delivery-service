package lt.vu.application.exception;

public abstract class ForbiddenException extends Exception {

    public ForbiddenException(String message) {
        super(message);
    }
}
