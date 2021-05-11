package lt.vu.application.order.exception;

import lt.vu.application.exception.NotFoundException;

public class OrderNotFoundException extends NotFoundException {

    private static final String message = "Order not found";

    public OrderNotFoundException() {
        super(message);
    }
}
