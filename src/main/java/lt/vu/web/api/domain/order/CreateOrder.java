package lt.vu.web.api.domain.order;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.*;
import lt.vu.persistence.orm.repository.OrderRepository;
import lt.vu.persistence.orm.repository.PackageOptionRepository;
import lt.vu.web.api.dto.postDtos.order.OrderDTO;
import lt.vu.web.api.dto.postDtos.order.SenderDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestScoped
@Getter @Setter
public class CreateOrder {

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private PackageOptionRepository packageOptionRepository;

    @Transactional
    public Order order(OrderDTO orderDTO){
        Order order = new Order();
        setOrderPackageOption(orderDTO, order);
        setOrderPickupDate(orderDTO, order);
        order.setSenderInfo(createUserInfo(orderDTO.getSender()));
        order.setRecipientInfo(createUserInfo(orderDTO.getRecipient()));
        order.setStatus(OrderStatus.NEW);
        order.setTotalPrice(0);
        orderRepository.persist(order);

        return order;
    }

    private void setOrderPackageOption(OrderDTO orderDTO, Order order) {
        PackageOption packageOption = packageOptionRepository.findOne(orderDTO.getPackageOptionId());
        order.setPackageOption(packageOption);
    }

    private void setOrderPickupDate(OrderDTO orderDTO, Order order) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date parsedDate = null;
        try {
            parsedDate = formatter.parse(orderDTO.getPickUpDate());
        } catch (ParseException e) {
            e.printStackTrace(); //TODO
        }
        order.setPickupDateTime(parsedDate);
    }

    private UserInfo createUserInfo(SenderDTO userData){
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(userData.getFirstName());
        userInfo.setLastName(userData.getLastName());
        userInfo.setEmail(userData.getEmail());
        userInfo.setPhoneNumber(userData.getPhoneNumber());
        userInfo.setAddress(extractAddress(userData));
        return userInfo;
    }

    private Address extractAddress(SenderDTO userData){
        Address address = new Address();
        address.setCity(userData.getAddress().getCity());
        address.setStreet(userData.getAddress().getStreet());
        address.setNumber("1");
        return address;
    }

    public CreateOrder() {
    }
}
