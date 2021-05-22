package lt.vu.application.order.service;

import lt.vu.persistence.orm.entities.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DefaultPriceCalculator implements PriceCalculator {
    @Inject
    private Iterable<ChainablePriceCalculator> calculators;

    public int calculate(Order order) {
        int totalPrice = order.getPackageOption().getPrice();

        for (ChainablePriceCalculator calculator : this.calculators) {
            if (calculator.canCalculate(order)) {
                totalPrice = calculator.calculate(totalPrice);
            }
        }

        return totalPrice;
    }
}
