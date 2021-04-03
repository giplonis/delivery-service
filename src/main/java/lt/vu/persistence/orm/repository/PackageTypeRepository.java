package lt.vu.persistence.orm.repository;

import lt.vu.persistence.orm.entities.PackageType;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@RequestScoped
public class PackageTypeRepository {

    @Inject
    private EntityManager entityManager;

    public List<PackageType> findAll() {
        return this.entityManager.createNamedQuery("PackageType.findAll", PackageType.class).getResultList();
    }
}
