package lt.vu.application.order.service.producer;

import lt.vu.application.order.service.calculator.ChainablePriceCalculator;
import lt.vu.application.order.service.calculator.FragilePriceCalculator;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Produces a list of ChainablePriceCalculator implementations for injection purposes.
 */
@RequestScoped
public class ChainableCalculatorProducer {

    @Inject
    private FragilePriceCalculator fragilePriceCalculator;

    @Produces
    public Iterable<ChainablePriceCalculator> produce() {
        return new ArrayList<>(
            Arrays.asList(this.fragilePriceCalculator)
        );
    }
}
