package lt.vu.application.security.exception;

import lt.vu.application.exception.ForbiddenException;

public class AccessForbiddenException extends ForbiddenException {

    private static final String message = "Access forbidden";

    public AccessForbiddenException() {
        super(message);
    }
}
