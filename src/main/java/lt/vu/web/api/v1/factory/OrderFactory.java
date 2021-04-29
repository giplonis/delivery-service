package lt.vu.web.api.v1.factory;

import lt.vu.persistence.orm.entities.*;
import lt.vu.persistence.orm.repository.PackageOptionRepository;
import lt.vu.web.api.v1.dto.order.PostOrderDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Date;

@RequestScoped
public class OrderFactory {

    @Inject
    private PackageOptionRepository packageOptionRepository;

    @Inject
    private UserInfoFactory userInfoFactory;

    public Order create(PostOrderDTO orderDTO) {
        Order order = new Order();

        PackageOption packageOption = this.packageOptionRepository.findOneById(orderDTO.getPackageOptionId());

        order.setSenderInfo(this.userInfoFactory.create(orderDTO.getSender()));
        order.setRecipientInfo(this.userInfoFactory.create(orderDTO.getRecipient()));
        order.setStatus(OrderStatus.NEW);
        order.setPickupDateTime(orderDTO.getPickUpDate());
        order.setPackageOption(packageOption);
        order.setTotalPrice(packageOption.getPrice());

        return order;
    }
}
