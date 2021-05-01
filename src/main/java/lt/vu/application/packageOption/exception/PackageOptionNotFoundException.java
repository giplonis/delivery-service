package lt.vu.application.packageOption.exception;

import lt.vu.application.exception.NotFoundException;

public class PackageOptionNotFoundException extends NotFoundException {

    private static final String message = "Package option not found";

    public PackageOptionNotFoundException() {
        super(message);
    }
}
