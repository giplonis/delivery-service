package lt.vu.persistence.orm.repository;

import lt.vu.persistence.orm.entities.Attribute;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
}
