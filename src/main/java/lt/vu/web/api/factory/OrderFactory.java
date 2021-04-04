package lt.vu.web.api.factory;

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
public class OrderFactory {

    @Inject
    private PackageOptionRepository packageOptionRepository;

    @Inject
    private UserInfoFactory userInfoFactory;

    public Order create(OrderDTO orderDTO){
        Order order = new Order();
        setOrderPackageOption(orderDTO, order);
        order.setSenderInfo(userInfoFactory.create(orderDTO.getSender()));
        order.setRecipientInfo(userInfoFactory.create(orderDTO.getRecipient()));
        order.setStatus(OrderStatus.NEW);
        order.setPickupDateTime(orderDTO.getPickUpDate());
        order.setTotalPrice(0);
        return order;
    }

    private void setOrderPackageOption(OrderDTO orderDTO, Order order) {
        PackageOption packageOption = packageOptionRepository.findOne(orderDTO.getPackageOptionId());
        order.setPackageOption(packageOption);
    }
}
