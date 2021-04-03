package lt.vu.web.api.controller.order;

import lt.vu.persistence.orm.entities.Order;
import lt.vu.web.api.domain.order.CreateOrder;
import lt.vu.web.api.dto.postDtos.order.OrderDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orders")
@RequestScoped
public class PostOrderController {

    @Inject
    private CreateOrder create;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAction(OrderDTO orderDTO){
        Order order = create.order(orderDTO);

        return Response.ok(order, MediaType.APPLICATION_JSON).build();
    }
}
