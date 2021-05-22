package lt.vu.application.order.service;

import lt.vu.persistence.orm.entities.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

public interface PriceCalculator {
    int calculate(Order order);
}
