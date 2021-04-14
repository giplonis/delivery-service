package lt.vu.persistence.orm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "PACKAGE_OPTION")
@NamedQueries({
    @NamedQuery(name = "PackageOption.findAll", query = "select po from PackageOption po"),
    @NamedQuery(name = "PackageOption.findByPackageType", query = "select po from PackageOption po WHERE po.packageType.id = :packageTypeId")
})
@Getter @Setter
public class PackageOption implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "PACKAGE_TYPE_ID", nullable = false)
    private PackageType packageType;

    @Column(name = "PRICE")
    private int price;

    @ManyToOne
    @JoinColumn(name = "PACKAGE_SIZE_ID", nullable = false)
    private PackageSize packageSize;

    @Column(name = "FRAGILE",
            columnDefinition = "boolean default false")
    private boolean fragile;

    public PackageOption() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageOption packageOption = (PackageOption) o;

        return packageOption.id == this.id
            && packageOption.price == this.price
            && packageOption.packageType.equals(this.packageType)
            && packageOption.packageSize.equals(this.packageSize)
            && packageOption.fragile == this.fragile;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.packageType, this.price, this.packageSize, this.fragile);
    }
}
