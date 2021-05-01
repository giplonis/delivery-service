package lt.vu.application.order.service;

import lombok.SneakyThrows;
import lt.vu.persistence.orm.entities.AttributeType;
import lt.vu.persistence.orm.entities.Order;
import lt.vu.persistence.orm.repository.AttributeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
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
