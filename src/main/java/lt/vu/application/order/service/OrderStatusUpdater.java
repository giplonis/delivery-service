package lt.vu.application.order.service;

import lt.vu.persistence.entities.Order;
import lt.vu.persistence.entities.OrderStatus;
import lt.vu.persistence.repository.OrderRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Acts as a fake cronjob which changes orders statuses to DELIVERED after some time.
 */
@ApplicationScoped
public class OrderStatusUpdater {

    @Inject
    private OrderRepository orderRepository;

    @Transactional
    public void updateNewOrders() {
        this.orderRepository.findNew()
                .forEach(this::updateStatus);
    }

    private void updateStatus(Order order) {
        order.setStatus(OrderStatus.DELIVERED);
        this.orderRepository.update(order);
    }
}
