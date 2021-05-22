package lt.vu.application.order.service.calculator;

import lt.vu.persistence.orm.entities.Order;

public interface PriceCalculator {

    int calculate(Order order);
}
