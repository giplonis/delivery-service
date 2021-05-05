package lt.vu.application.exception;

public abstract class UnauthorizedException extends Exception {

    public UnauthorizedException(String message) {
        super(message);
    }
}
