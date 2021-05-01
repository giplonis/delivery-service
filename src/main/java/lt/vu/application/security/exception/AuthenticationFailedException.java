package lt.vu.application.security.exception;

public abstract class AuthenticationFailedException extends Exception {

    public AuthenticationFailedException(String message) {
        super(message);
    }
}
