package lt.vu.persistence.orm.dao;

import lt.vu.persistence.orm.entities.Example;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class ExampleDAO {

    @Inject
    private EntityManager em;

    public void persist(Example example){
        this.em.persist(example);
    }

    public Example findOne(Integer id){
        return em.find(Example.class, id);
    }

    public Example update(Example example){
        return em.merge(example);
    }
}
