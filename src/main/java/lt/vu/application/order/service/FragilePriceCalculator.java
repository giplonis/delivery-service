package lt.vu.application.order.service;

import lt.vu.persistence.orm.entities.AttributeType;
import lt.vu.persistence.orm.entities.Order;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FragilePriceCalculator implements ChainablePriceCalculator {

    @Override
    public boolean canCalculate(Order order) {
        return order
                .getAttributes()
                .stream()
                .anyMatch(a -> a.getType() == AttributeType.FRAGILE);
    }

    @Override
    public int calculate(int basePrice) {
        return basePrice + 200;
    }
}
