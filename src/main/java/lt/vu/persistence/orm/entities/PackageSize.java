package lt.vu.persistence.orm.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
}
