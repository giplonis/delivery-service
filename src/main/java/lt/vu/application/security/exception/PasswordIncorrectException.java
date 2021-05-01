package lt.vu.application.security.exception;

public class PasswordIncorrectException extends AuthenticationFailedException {

    private static final String message = "Password is incorrect";

    public PasswordIncorrectException() {
        super(message);
    }
}
