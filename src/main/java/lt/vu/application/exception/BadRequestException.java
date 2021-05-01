package lt.vu.application.exception;

public abstract class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }
}
