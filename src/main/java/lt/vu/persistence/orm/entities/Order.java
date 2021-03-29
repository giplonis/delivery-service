package lt.vu.persistence.orm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "Order.findAll", query = "select e from Order as e")
})
@Getter
@Setter
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Boolean status;

    private Date pickupDateTime;

    private float totalPrice;

    @OneToOne
    private Package orderPackage;

    @OneToOne
    private UserInfo senderInfo;

    @OneToOne
    private UserInfo recipientInfo;

    @OneToOne
    private User registeredSender;

}
