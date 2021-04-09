package lt.vu.persistence.orm.repository;

import lt.vu.persistence.orm.entities.PackageOption;
import lt.vu.persistence.orm.entities.PackageType;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@RequestScoped
public class PackageOptionRepository {

    @Inject
    private EntityManager entityManager;

    public List<PackageOption> findAll() {
        return this.entityManager.createNamedQuery("PackageOption.findAll", PackageOption.class).getResultList();
    }

    public List<PackageOption> findByType(int packageTypeId) {
        return this.entityManager.createNamedQuery("PackageOption.findByType", PackageOption.class).setParameter("packageTypeId",packageTypeId).getResultList();
    }
}
