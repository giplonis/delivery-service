package lt.vu.persistence.orm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && city.equals(address.city) && street.equals(address.street) && nr.equals(address.nr) && Objects.equals(user, address.user) && Objects.equals(guest, address.guest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, street, nr, user, guest);
    }
}
