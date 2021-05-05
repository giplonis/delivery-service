package lt.vu.application.security.exception;

import lt.vu.application.exception.BadRequestException;

public class PasswordIncorrectException extends BadRequestException {

    private static final String message = "Password is incorrect";

    public PasswordIncorrectException() {
        super(message);
    }
}
