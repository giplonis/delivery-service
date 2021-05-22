package lt.vu.persistence.repository;

import lt.vu.application.config.AppConfig;
import lt.vu.application.order.exception.OrderNotFoundException;
import lt.vu.persistence.entities.Order;
import lt.vu.persistence.entities.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

@RequestScoped
public class OrderRepository {

    @Inject
    private EntityManager entityManager;

    public void persist(Order order) {
        this.entityManager.persist(order);
    }

    public void update(Order instance) {
        this.entityManager.merge(instance);
    }

    public List<Order> findAll() {
        return this.entityManager
                .createNamedQuery("Order.findAll", Order.class)
                .getResultList();
    }

    public List<Order> findBySender(User user) {
        return this.entityManager
                .createNamedQuery("Order.findBySender", Order.class)
                .setParameter("sender", user)
                .setParameter("email", user.getEmail())
                .getResultList();
    }

    public List<Order> findNew() {
        // Older than past 2 minutes
        Date date = new Date(System.currentTimeMillis() - AppConfig.ORDER_DELIVERY_TIME_MINUTES * 60 * 1000);

        return this.entityManager
                .createNamedQuery("Order.findNew", Order.class)
                .setParameter("date", date)
                .getResultList();
    }

    public Order findOneById(int id) throws OrderNotFoundException {
        try {
            return this.entityManager
                    .createNamedQuery("Order.findOneById", Order.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new OrderNotFoundException();
        }
    }

    public List<Order> findByRecipient(User recipient) {
        return this.entityManager
                .createNamedQuery("Order.findByRecipient", Order.class)
                .setParameter("email", recipient.getEmail())
                .getResultList();
    }
}
