package lt.vu.persistence.orm.repository;

import lt.vu.application.exception.NotFoundException;
import lt.vu.application.packageOption.exception.PackageOptionNotFoundException;
import lt.vu.persistence.orm.entities.PackageOption;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@RequestScoped
public class PackageOptionRepository {

    @Inject
    private EntityManager entityManager;

    public List<PackageOption> findAll() {
        return this.entityManager.createNamedQuery("PackageOption.findAll", PackageOption.class).getResultList();
    }

    public PackageOption findOneById(int id) throws NotFoundException {
        try {
            return this.entityManager.find(PackageOption.class, id);
        } catch (NoResultException e) {
            throw new PackageOptionNotFoundException();
        }
    }

    public List<PackageOption> findByPackageType(int packageTypeId) {
        return this.entityManager
                .createNamedQuery("PackageOption.findByPackageType", PackageOption.class)
                .setParameter("packageTypeId", packageTypeId)
                .getResultList();
    }
}
