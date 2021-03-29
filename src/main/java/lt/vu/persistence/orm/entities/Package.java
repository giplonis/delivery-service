package lt.vu.persistence.orm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
}
