package lt.vu.persistence.orm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "PACKAGE_TYPE")
@NamedQueries({
    @NamedQuery(name = "PackageType.findAll", query = "select pt from PackageType pt")
})
@Getter @Setter
public class PackageType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    public PackageType() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageType packageType = (PackageType) o;

        return packageType.id == this.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title);
    }
}
