package lt.vu.persistence.orm.repository;

import lt.vu.application.exception.NotFoundException;
import lt.vu.application.user.exception.UserNotFoundException;
import lt.vu.persistence.orm.entities.User;
import lt.vu.persistence.orm.entities.UserRole;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@RequestScoped
public class UserRepository {

    @Inject
    private EntityManager entityManager;

    public void persist(User user) {
        this.entityManager.persist(user);
    }

    public void update(User user) {
        this.entityManager.merge(user);
    }

    public List<User> findAll() {
        return this.entityManager
                .createNamedQuery("User.findAll", User.class)
                .getResultList();
    }

    public User findOneById(int id) throws NotFoundException {
        try {
            return this.entityManager
                    .createNamedQuery("User.findOneById", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new UserNotFoundException();
        }
    }

    public User findOneByEmail(String email) throws NotFoundException {
        try {
            return this.entityManager
                    .createNamedQuery("User.findOneByEmail", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new UserNotFoundException();
        }
    }

    public List<User> findByRole(String role) {
        return this.entityManager
                .createNamedQuery("User.findByRole", User.class)
                .setParameter("role", role)
                .getResultList();
    }
}
