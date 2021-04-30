package lt.vu.web.api.v1.controller.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.vu.persistence.orm.entities.Order;
import lt.vu.persistence.orm.repository.OrderRepository;
import lt.vu.web.api.v1.dto.order.ListOrderDTO;
import lt.vu.web.api.v1.dto.order.GetOrderDTO;
import lt.vu.web.api.v1.helper.OrderManager;
import lt.vu.web.api.v1.exception.ExceptionDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/orders")
@RequestScoped
public class ListOrderController {

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private OrderManager orderManager;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Fetch list of user's orders",
        tags = { "Order" },
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = ListOrderDTO.class))
            ),
            @ApiResponse(
                responseCode = "500",
                content = @Content(schema = @Schema(implementation = ExceptionDTO.class))
            )
        }
    )
    public Response listAction() {
        // TODO: Fetch orders only for current user
        this.orderManager.correctStatusOfAllOrders();
        List<Order> orders = this.orderRepository.findAll();

        return Response
                .ok(new ListOrderDTO(GetOrderDTO.createMany(orders)))
                .build();
    }
}
