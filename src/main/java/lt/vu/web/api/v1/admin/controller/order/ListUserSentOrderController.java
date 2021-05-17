package lt.vu.web.api.v1.admin.controller.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.vu.application.exception.NotFoundException;
import lt.vu.application.order.service.OrderStatusUpdater;
import lt.vu.infrastructure.security.Authorized;
import lt.vu.persistence.orm.entities.Order;
import lt.vu.persistence.orm.entities.User;
import lt.vu.persistence.orm.entities.UserRole;
import lt.vu.persistence.orm.repository.OrderRepository;
import lt.vu.persistence.orm.repository.UserRepository;
import lt.vu.web.api.v1.controller.security.CurrentUserAwareController;
import lt.vu.web.api.v1.dto.order.GetOrderDTO;
import lt.vu.web.api.v1.dto.order.ListOrderDTO;
import lt.vu.web.api.v1.exception.ExceptionDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/admin/orders")
@RequestScoped
public class ListUserSentOrderController extends CurrentUserAwareController {

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private OrderStatusUpdater orderStatusUpdater;

    @Inject
    private UserRepository userRepository;

    @GET
    @Path("/user/{id}/sent")
    @Authorized(role = UserRole.ADMIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Fetch list of user's sent orders",
        tags = { "Order" },
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
                responseCode = "500",
                content = @Content(schema = @Schema(implementation = ExceptionDTO.class))
            )
        }
    )
    public Response listAction(@PathParam("id") Integer userId) throws NotFoundException {
        // This acts as a fake cronjob to update old order statuses into DELIVERED
        this.orderStatusUpdater.updateNewOrders();

        User userById = userRepository.findOneById(userId);
        List<Order> orders = this.orderRepository.findBySender(userById);

        return Response
                .ok(new ListOrderDTO(GetOrderDTO.createMany(orders)))
                .build();
    }
}
