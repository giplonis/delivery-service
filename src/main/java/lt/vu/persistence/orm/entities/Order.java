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
@Getter @Setter
@Table(name = "ORDERS")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Boolean status;

    @Column(name = "PICKUP_DATE_TIME")
    private Date pickupDateTime;

    @Column(name = "TOTAL_PRICE")
    private float totalPrice;

    @OneToOne(mappedBy = "order")
    private Package orderPackage;

    @OneToOne(mappedBy = "senderOrder", cascade = CascadeType.ALL)
    private UserInfo senderInfo;

    @OneToOne(mappedBy = "recipientOrder", cascade = CascadeType.ALL)
    private UserInfo recipientInfo;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User registeredSender;

    public Order() {
    }
}
