package lt.vu.persistence.orm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "`ORDER`")
@NamedQueries({
    @NamedQuery(name = "Order.findAll", query = "select o from Order o order by o.createdAt desc")
})
@Getter @Setter
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CREATED_AT", nullable = false)
    private final Date createdAt = new Date();

    @Column(name = "STATUS", nullable = false)
    private OrderStatus status;

    @Column(name = "PICKUP_DATE_TIME", nullable = false)
    private Date pickupDateTime;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private int totalPrice;

    @ManyToOne
    @JoinColumn(name = "PACKAGE_OPTION_ID", nullable = false)
    private PackageOption packageOption;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SENDER_INFO_ID", nullable = false)
    private UserInfo senderInfo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RECIPIENT_INFO_ID", nullable = false)
    private UserInfo recipientInfo;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User sender;

    public Order() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;

        return order.id == this.id
            && order.totalPrice == this.totalPrice
            && order.status.equals(this.status)
            && order.pickupDateTime.equals(this.pickupDateTime)
            && order.packageOption.equals(this.packageOption)
            && order.senderInfo.equals(this.senderInfo)
            && order.recipientInfo.equals(this.recipientInfo)
            && order.sender.equals(this.sender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.id,
            this.status,
            this.pickupDateTime,
            this.totalPrice,
            this.packageOption,
            this.senderInfo,
            this.recipientInfo,
            this.sender
        );
    }
}
