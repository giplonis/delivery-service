package lt.vu.web.api.controller.order;

import lt.vu.persistence.orm.entities.Order;
import lt.vu.persistence.orm.repository.OrderRepository;
import lt.vu.web.api.factory.OrderFactory;
import lt.vu.web.api.dto.postDtos.order.OrderDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orders")
@RequestScoped
public class PostOrderController {

    @Inject
    private OrderFactory orderFactory;

    @Inject
    private OrderRepository orderRepository;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createAction(OrderDTO orderDTO){
        Order order = orderFactory.create(orderDTO);
        orderRepository.persist(order);
        return Response.ok(order, MediaType.APPLICATION_JSON).build();
    }
}
