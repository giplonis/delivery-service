package lt.vu.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "PACKAGE_SIZE")
@NamedQueries({
    @NamedQuery(name = "PackageSize.findAll", query = "select ps from PackageSize ps")
})
@Getter @Setter
public class PackageSize implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "MAX_WEIGHT")
    private int maxWeight;

    @Column(name = "LENGTH")
    private int length;

    @Column(name = "HEIGHT")
    private int height;

    @Column(name = "WIDTH")
    private int width;

    public PackageSize() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageSize packageSize = (PackageSize) o;

        return packageSize.id == this.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
