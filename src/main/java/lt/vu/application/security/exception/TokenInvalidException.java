package lt.vu.application.security.exception;

public class TokenInvalidException extends Exception {

    private static final String message = "The provided token is invalid";

    public TokenInvalidException() {
        super(message);
    }
}
