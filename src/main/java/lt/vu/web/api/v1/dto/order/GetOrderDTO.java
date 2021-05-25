package lt.vu.web.api.v1.dto.order;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.Order;
import lt.vu.web.api.v1.dto.attribute.GetAttributeDTO;
import lt.vu.web.api.v1.dto.packageOption.GetPackageOptionDTO;
import lt.vu.web.api.v1.dto.user.GetUserDTO;
import lt.vu.web.api.v1.dto.userInfo.GetUserInfoDTO;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class GetOrderDTO {

    private int id;

    private Date createdAt;

    private String status;

    private Date pickupDateTime;

    private int totalPrice;

    private GetUserInfoDTO senderInfo;

    private GetUserInfoDTO recipientInfo;

    private GetUserDTO sender;

    private GetPackageOptionDTO packageOption;

    private List<GetAttributeDTO> attributes;

    private long version;

    public static GetOrderDTO createFromEntity(Order order) {
        GetOrderDTO dto = new GetOrderDTO();

        dto.setId(order.getId());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setStatus(order.getStatus().toString().toUpperCase());
        dto.setPickupDateTime(order.getPickupDateTime());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setSenderInfo(GetUserInfoDTO.createFromEntity(order.getSenderInfo()));
        dto.setRecipientInfo(GetUserInfoDTO.createFromEntity(order.getRecipientInfo()));
        dto.setPackageOption(GetPackageOptionDTO.createFromEntity(order.getPackageOption()));
        dto.setAttributes(GetAttributeDTO.createMany(order.getAttributes()));
        dto.setVersion(order.getVersion());

        if (order.getSender() != null) {
            dto.setSender(GetUserDTO.createFromEntity(order.getSender()));
        }

        return dto;
    }

    public static List<GetOrderDTO> createMany(List<Order> orders) {
        return orders
                .stream()
                .map(GetOrderDTO::createFromEntity)
                .collect(Collectors.toList());
    }
}
