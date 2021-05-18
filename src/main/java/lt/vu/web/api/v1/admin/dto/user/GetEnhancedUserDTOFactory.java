package lt.vu.web.api.v1.admin.dto.user;

import lt.vu.persistence.orm.entities.Order;
import lt.vu.persistence.orm.entities.User;
import lt.vu.persistence.orm.repository.OrderRepository;
import lt.vu.web.api.v1.dto.address.GetAddressDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GetEnhancedUserDTOFactory {

    @Inject
    private OrderRepository orderRepository;

    public GetEnhancedUserDTO createFromEntity(User user) {
        GetEnhancedUserDTO dto = new GetEnhancedUserDTO();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        if (user.getAddress() != null) {
            dto.setAddress(GetAddressDTO.createFromEntity(user.getAddress()));
        }

        List<Order> orders = this.orderRepository.findBySender(user);
        dto.setTotalOrders(orders.toArray().length);

        if (!orders.isEmpty()) {
            dto.setLastOrderDate(orders.get(0).getCreatedAt());
        }

        return dto;
    }

    public List<GetEnhancedUserDTO> createMany(List<User> users) {
        return users
                .stream()
                .map(this::createFromEntity)
                .collect(Collectors.toList());
    }
}
