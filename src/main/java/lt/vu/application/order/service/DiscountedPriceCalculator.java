package lt.vu.application.order.service;

import lt.vu.persistence.orm.entities.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;

@Alternative
@Specializes
@ApplicationScoped
public class DiscountedPriceCalculator extends DefaultPriceCalculator{

    private final int DISCOUNT_AMOUNT = 20;

    @Override
    public int calculate(Order order) {
        return Math.max(0, super.calculate(order) - DISCOUNT_AMOUNT);
    }
}
