package lt.vu.application.order.service;

import lt.vu.infrastructure.logger.Logger;
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

    @Inject
    private Logger logger;

    @Transactional
    public void updateNewOrders() {
        try {
            this.orderRepository.findNew()
                    .forEach(this::updateStatus);
        } catch (Exception e) {
            this.logger.error(e);
            this.orderRepository.findNew()
                    .forEach(this::updateStatus);
        }
    }

    private void updateStatus(Order order) {
        order.setStatus(OrderStatus.DELIVERED);
        this.orderRepository.update(order);
    }
}
