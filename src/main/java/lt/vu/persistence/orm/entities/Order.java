package lt.vu.persistence.orm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Float.compare(order.totalPrice, totalPrice) == 0 && status.equals(order.status) && pickupDateTime.equals(order.pickupDateTime) && orderPackage.equals(order.orderPackage) && Objects.equals(senderInfo, order.senderInfo) && Objects.equals(recipientInfo, order.recipientInfo) && Objects.equals(registeredSender, order.registeredSender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, pickupDateTime, totalPrice, orderPackage, senderInfo, recipientInfo, registeredSender);
    }

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
