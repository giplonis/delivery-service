package lt.vu.persistence.orm.repository;

import lt.vu.persistence.orm.entities.PackageOption;
import lt.vu.persistence.orm.entities.PackageSize;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@RequestScoped
public class PackageOptionRepository {

    @Inject
    private EntityManager entityManager;

    public PackageOption findOne(Integer id){
        return entityManager.find(PackageOption.class, id);
    }
}
