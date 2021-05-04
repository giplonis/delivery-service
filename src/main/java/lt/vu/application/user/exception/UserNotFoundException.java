package lt.vu.application.user.exception;

import lt.vu.application.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    private static final String message = "User not found";

    public UserNotFoundException() {
        super(message);
    }
}
