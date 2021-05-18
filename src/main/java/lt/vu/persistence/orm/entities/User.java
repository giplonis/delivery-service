package lt.vu.persistence.orm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "USER")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "select u from User u"),
    @NamedQuery(name = "User.findOneByEmail", query = "select u from User u where u.email = :email"),
    @NamedQuery(name = "User.findOneById", query = "select u from User u where u.id = :id"),
    @NamedQuery(name = "User.findByRole", query = "select u from User u inner join u.roles r where r = :role"),
})
@Getter @Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @ElementCollection(targetClass = String.class)
    @Column(name = "ROLE")
    @CollectionTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") })
    private List<String> roles;

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;

        return user.id == this.id
            && user.firstName.equals(this.firstName)
            && user.lastName.equals(this.lastName)
            && user.email.equals(this.email)
            && user.phoneNumber.equals(this.phoneNumber)
            && user.password.equals(this.password)
            && user.address.equals(this.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.id,
            this.firstName,
            this.lastName,
            this.email,
            this.phoneNumber,
            this.password,
            this.address
        );
    }
}
