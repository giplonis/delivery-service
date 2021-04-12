package lt.vu.persistence.orm.repository;

import lt.vu.persistence.orm.entities.Order;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@RequestScoped
public class OrderRepository {

    @Inject
    private EntityManager entityManager;

    public void persist(Order order){
        this.entityManager.persist(order);
    }

    public List<Order> findAll() {
        return this.entityManager.createNamedQuery("Order.findAll", Order.class).getResultList();
    }
}
