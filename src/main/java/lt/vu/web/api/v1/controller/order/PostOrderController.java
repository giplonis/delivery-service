package lt.vu.web.api.v1.controller.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.vu.persistence.orm.entities.Order;
import lt.vu.persistence.orm.repository.OrderRepository;
import lt.vu.web.api.v1.factory.OrderFactory;
import lt.vu.web.api.v1.dto.order.PostOrderDTO;

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
    @Operation(
        summary = "Submit new order",
        tags = { "Order" },
        responses = {
            @ApiResponse(
                responseCode = "201"
            )
        }
    )
    public Response createAction(
        @RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = PostOrderDTO.class))
        ) PostOrderDTO orderDTO) {

        Order order = null;
        try {
            order = this.orderFactory.create(orderDTO);
        }
        catch(IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        this.orderRepository.persist(order);

        return Response.status(Response.Status.CREATED).build();
    }
}