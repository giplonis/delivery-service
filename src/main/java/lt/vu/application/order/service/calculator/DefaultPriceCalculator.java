package lt.vu.application.order.service.calculator;

import lt.vu.persistence.entities.Order;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
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
