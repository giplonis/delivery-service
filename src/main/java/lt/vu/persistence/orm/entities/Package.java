package lt.vu.persistence.orm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "Order.findAll", query = "select e from Package as e")
})
@Getter @Setter
public class Package implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private PackageType packageType;

    private float price;

    @OneToOne
    private PackageSize packageSize;
}
