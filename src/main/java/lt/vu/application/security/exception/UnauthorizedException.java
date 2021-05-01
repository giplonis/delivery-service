package lt.vu.application.security.exception;

public class UnauthorizedException extends Exception {

    private static final String message = "Authentication credentials missing";

    public UnauthorizedException() {
        super(message);
    }
}
