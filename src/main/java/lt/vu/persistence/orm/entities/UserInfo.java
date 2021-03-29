package lt.vu.persistence.orm.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
}
