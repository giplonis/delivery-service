package lt.vu.application.order.service.calculator;

import lt.vu.persistence.entities.Order;

public interface ChainablePriceCalculator {

    /**
     * Verify if this calculator can modify price for provided order.
     */
    boolean canCalculate(Order order);

    /**
     * Modify base price
     */
    int calculate(int basePrice);
}
