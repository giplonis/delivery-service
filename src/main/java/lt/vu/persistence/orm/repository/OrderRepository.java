package lt.vu.persistence.orm.repository;

import lt.vu.persistence.orm.entities.Order;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
        return this.entityManager.createNamedQuery("Order.findAll", Order.class).getResultList();
    }

    public List<Order> findNew() {
        // Older than past 2 minutes
        Date date = new Date(System.currentTimeMillis() - 2 * 60 * 1000);

        return this.entityManager
                .createNamedQuery("Order.findNew", Order.class)
                .setParameter("date", date)
                .getResultList();
    }
}
