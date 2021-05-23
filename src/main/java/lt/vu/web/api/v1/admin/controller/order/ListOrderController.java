package lt.vu.web.api.v1.admin.controller.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.vu.application.exception.NotFoundException;
import lt.vu.infrastructure.interceptors.LoggedAction;
import lt.vu.infrastructure.security.Authorized;
import lt.vu.persistence.orm.entities.Order;
import lt.vu.persistence.orm.entities.UserRole;
import lt.vu.persistence.orm.repository.OrderRepository;
import lt.vu.persistence.orm.repository.UserRepository;
import lt.vu.web.api.v1.dto.order.ListOrderDTO;
import lt.vu.web.api.v1.dto.order.GetOrderDTO;
import lt.vu.web.api.v1.exception.ExceptionDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/admin/orders")
@RequestScoped
public class ListOrderController {

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private UserRepository userRepository;

    @GET
    @Path("/")
    @LoggedAction
    @Authorized(role = UserRole.ADMIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Fetch a complete list of all orders",
        tags = { "Admin-Order" },
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = ListOrderDTO.class))
            ),
            @ApiResponse(
                responseCode = "400",
                content = @Content(schema = @Schema(implementation = ExceptionDTO.class))
            ),
            @ApiResponse(
                responseCode = "404",
                content = @Content(schema = @Schema(implementation = ExceptionDTO.class))
            ),
            @ApiResponse(
                responseCode = "500",
                content = @Content(schema = @Schema(implementation = ExceptionDTO.class))
            )
        }
    )
    public Response listAction(
        @QueryParam("senderId") Integer senderId,
        @QueryParam("recipientId") Integer recipientId
    ) throws NotFoundException {
        List<Order> orders = this.getOrders(senderId, recipientId);

        return Response
                .ok(new ListOrderDTO(GetOrderDTO.createMany(orders)))
                .build();
    }

    private List<Order> getOrders(Integer senderId, Integer recipientId) throws NotFoundException {
        if (senderId != null) {
            return this.orderRepository.findBySender(this.userRepository.findOneById(senderId));
        }

        if (recipientId != null) {
            return this.orderRepository.findByRecipient(this.userRepository.findOneById(recipientId));
        }

        return this.orderRepository.findAll();
    }
}
