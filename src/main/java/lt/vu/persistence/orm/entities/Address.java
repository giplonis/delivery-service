package lt.vu.persistence.orm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "Address.findAll", query = "select e from Address as e")
})
@Getter
@Setter
@Table(name = "ADDRESSES")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String city;

    private String street;

    private String nr;

    @OneToOne
    private User user;

    @OneToOne
    private UserInfo guest;

    public Address() {
    }
}
