package lt.vu.persistence.repository;

import lt.vu.application.attribute.exception.AttributeNotFoundException;
import lt.vu.application.exception.NotFoundException;
import lt.vu.persistence.entities.Attribute;
import lt.vu.persistence.entities.AttributeType;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@RequestScoped
public class AttributeRepository {

    @Inject
    private EntityManager entityManager;

    public void persist(Attribute attribute){
        this.entityManager.persist(attribute);
    }

    public List<Attribute> findAll() {
        return this.entityManager
                .createNamedQuery("Attribute.findAll", Attribute.class)
                .getResultList();
    }

    public List<Attribute> findAllByIds(List<Integer> ids) {
        return this.entityManager
                .createNamedQuery("Attribute.findAllByIds", Attribute.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    public Attribute findOneByType(AttributeType type) throws NotFoundException {
        try {
            return this.entityManager
                    .createNamedQuery("Attribute.findOneByType", Attribute.class)
                    .setParameter("type", type)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new AttributeNotFoundException();
        }
    }
}
