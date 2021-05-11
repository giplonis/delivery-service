package lt.vu.application.order.service;

import lt.vu.persistence.orm.entities.Order;
import lt.vu.persistence.orm.entities.OrderStatus;
import lt.vu.persistence.orm.repository.OrderRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Acts as a fake cronjob which changes orders statuses to DELIVERED after some time.
 */
@ApplicationScoped
public class OrderStatusUpdater {

    @Inject
    private OrderRepository orderRepository;

    public void updateNewOrders() {
        this.orderRepository.findNew()
                .stream()
                .forEach(this::updateStatus);
    }

    private void updateStatus(Order order) {
        order.setStatus(OrderStatus.DELIVERED);
        this.orderRepository.update(order);
    }
}
