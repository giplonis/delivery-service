package lt.vu.persistence.orm.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "PackageSize.findAll", query = "select e from PackageSize as e")
})
@Getter
@Setter
@Table(name = "PACKAGE_SIZES")
public class PackageSize implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Column(name = "MAX_WEIGHT")
    private float maxWeight;

    private float length;

    private float height;

    private float width;

    public PackageSize() {
    }

    @OneToOne
    private Package orderPackage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageSize that = (PackageSize) o;
        return id == that.id && Float.compare(that.maxWeight, maxWeight) == 0 && Float.compare(that.length, length) == 0 && Float.compare(that.height, height) == 0 && Float.compare(that.width, width) == 0 && title.equals(that.title) && Objects.equals(orderPackage, that.orderPackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, maxWeight, length, height, width, orderPackage);
    }
}
