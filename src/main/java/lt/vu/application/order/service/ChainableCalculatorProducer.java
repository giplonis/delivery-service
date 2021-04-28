package lt.vu.application.order.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Produces a list of ChainablePriceCalculator implementations for injection purposes.
 */
@ApplicationScoped
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
