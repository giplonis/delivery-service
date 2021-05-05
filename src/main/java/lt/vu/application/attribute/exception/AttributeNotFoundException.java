package lt.vu.application.attribute.exception;

import lt.vu.application.exception.NotFoundException;

public class AttributeNotFoundException extends NotFoundException {

    private static final String message = "Attribute not found";

    public AttributeNotFoundException() {
        super(message);
    }
}
