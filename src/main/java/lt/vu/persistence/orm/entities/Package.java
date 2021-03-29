package lt.vu.persistence.orm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Package.findAll", query = "select e from Package as e")
})
@Getter @Setter
@Table(name = "PACKAGES")
public class Package implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "PACKAGE_TYPE")
    private PackageType packageType;

    private float price;

    @OneToOne
    private Order order;

    @OneToOne(mappedBy = "orderPackage")
    private PackageSize packageSize;

    public Package() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return id == aPackage.id && Float.compare(aPackage.price, price) == 0 && packageType == aPackage.packageType && order.equals(aPackage.order) && packageSize.equals(aPackage.packageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, packageType, price, order, packageSize);
    }
}
