package lt.vu.web.api.v1.helper;

import lt.vu.persistence.orm.entities.Order;
import lt.vu.persistence.orm.entities.OrderStatus;
import lt.vu.persistence.orm.repository.OrderRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class OrderManager {

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private TimeManager timeManager;

    public void checkStatusOfAllOrders() {
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            System.out.println(order.getId()
                    + " is "
                    + order.getStatus()
                    + ". Needs change? - "
                    + shouldStatusBeChangedIntoDelivered(order)
            );
            if (shouldStatusBeChangedIntoDelivered(order)) {
                order.setStatus(OrderStatus.DELIVERED);
                orderRepository.save(order);
            }
        }
    }

    public boolean shouldStatusBeChangedIntoDelivered(Order order) {
        if (order.getStatus() == OrderStatus.DELIVERED)
            return false;
        return timeManager.isExpired(
                order.getPickupDateTime(),
                0,
                0,
                2,
                1
        );
    }
}
