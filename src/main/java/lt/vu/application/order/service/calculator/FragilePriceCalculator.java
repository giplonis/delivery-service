package lt.vu.application.order.service.calculator;

import lombok.SneakyThrows;
import lt.vu.persistence.entities.AttributeType;
import lt.vu.persistence.entities.Order;
import lt.vu.persistence.repository.AttributeRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class FragilePriceCalculator implements ChainablePriceCalculator {

    @Inject
    private AttributeRepository attributeRepository;

    private final AttributeType supportedType = AttributeType.FRAGILE;

    @Override
    public boolean canCalculate(Order order) {
        return order
                .getAttributes()
                .stream()
                .anyMatch(a -> a.getType() == this.supportedType);
    }

    @Override
    @SneakyThrows
    public int calculate(int basePrice) {
        return basePrice + this.attributeRepository.findOneByType(this.supportedType).getAdditionalPrice();
    }
}
