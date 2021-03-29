package lt.vu.persistence.orm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "select e from User as e")
})
@Getter
@Setter
@Table(name = "USERS")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    private String password;

    public User() {
    }

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "registeredSender")
    private List<Order> orders = new ArrayList<>();
}
