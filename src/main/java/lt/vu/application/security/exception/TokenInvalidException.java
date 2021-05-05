package lt.vu.application.security.exception;

import lt.vu.application.exception.UnauthorizedException;

public class TokenInvalidException extends UnauthorizedException {

    private static final String message = "The provided token is invalid";

    public TokenInvalidException() {
        super(message);
    }
}
