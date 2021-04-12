package lt.vu.web.api.v1.dto.get.order;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.Order;
import lt.vu.web.api.v1.dto.get.user.UserDTO;
import lt.vu.web.api.v1.dto.get.userInfo.UserInfoDTO;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class OrderDTO {

    private int id;

    private Date createdAt;

    private String status;

    private Date pickupDateTime;

    private int totalPrice;

    private UserInfoDTO senderInfo;

    private UserInfoDTO recipientInfo;

    private UserDTO sender;

    public static OrderDTO createFromEntity(Order order) {
        OrderDTO dto = new OrderDTO();

        dto.setId(order.getId());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setStatus(order.getStatus().toString().toUpperCase());
        dto.setPickupDateTime(order.getPickupDateTime());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setSenderInfo(UserInfoDTO.createFromEntity(order.getSenderInfo()));
        dto.setRecipientInfo(UserInfoDTO.createFromEntity(order.getRecipientInfo()));
        if (order.getSender() != null) {
            dto.setSender(UserDTO.createFromEntity(order.getSender()));
        }

        return dto;
    }

    public static List<OrderDTO> createMany(List<Order> orders) {
        return orders
                .stream()
                .map(OrderDTO::createFromEntity)
                .collect(Collectors.toList());
    }
}
