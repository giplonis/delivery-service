package lt.vu.application.security.exception;

import lt.vu.application.exception.UnauthorizedException;

public class CredentialsMissingException extends UnauthorizedException {

    private static final String message = "Authorization credentials missing";

    public CredentialsMissingException() {
        super(message);
    }
}
