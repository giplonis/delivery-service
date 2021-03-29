package lt.vu.persistence.orm.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "UserInfo.findAll", query = "select e from UserInfo as e")
})
@Getter
@Setter
@Table(name = "USER_INFOS")
public class UserInfo implements Serializable {

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

    @OneToOne(mappedBy = "guest",cascade = CascadeType.ALL)
    private Address address;

    @OneToOne
    private Order senderOrder;

    @OneToOne
    private Order recipientOrder;

    public UserInfo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return id == userInfo.id && firstName.equals(userInfo.firstName) && lastName.equals(userInfo.lastName) && email.equals(userInfo.email) && phoneNumber.equals(userInfo.phoneNumber) && address.equals(userInfo.address) && Objects.equals(senderOrder, userInfo.senderOrder) && Objects.equals(recipientOrder, userInfo.recipientOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phoneNumber, address, senderOrder, recipientOrder);
    }
}
