package lt.vu.persistence.orm.repository;

import lt.vu.persistence.orm.entities.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@RequestScoped
public class UserRepository {

    @Inject
    private EntityManager entityManager;

    public void persist(User user) {
        this.entityManager.persist(user);
    }

    public List<User> findAll() {
        return this.entityManager
                .createNamedQuery("User.findAll", User.class)
                .getResultList();
    }

    public User findOneByEmail(String email) {
        return this.entityManager
                .createNamedQuery("User.findOneByEmail", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
