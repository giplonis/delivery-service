package lt.vu.application.user.exception;

public class UserAlreadyExistsException extends Exception {

    private static final String message = "User already exists";

    public UserAlreadyExistsException() {
        super(message);
    }
}
