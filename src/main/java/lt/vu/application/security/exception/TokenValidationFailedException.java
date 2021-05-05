package lt.vu.application.security.exception;

import lt.vu.application.exception.UnauthorizedException;

public class TokenValidationFailedException extends UnauthorizedException {

    public TokenValidationFailedException(String message) {
        super(message);
    }
}
