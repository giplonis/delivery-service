package lt.vu.web.api.v1.controller.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.vu.application.exception.BadRequestException;
import lt.vu.application.exception.NotFoundException;
import lt.vu.application.order.service.OrderStatusUpdater;
import lt.vu.infrastructure.security.Authorized;
import lt.vu.persistence.orm.entities.Order;
import lt.vu.persistence.orm.repository.OrderRepository;
import lt.vu.web.api.v1.controller.security.CurrentUserAwareController;
import lt.vu.web.api.v1.dto.order.PutOrderStatusDTO;
import lt.vu.web.api.v1.dto.security.GetTokenDTO;
import lt.vu.web.api.v1.exception.ExceptionDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/update-order-status")
@RequestScoped
public class PutOrderStatusController extends CurrentUserAwareController {

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private OrderStatusUpdater orderStatusUpdater;

    @PUT
    @Path("/")
    @Authorized //todo only for admin
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Operation(
        summary = "Change order's status",
        description = "Changes order status to given.",
        tags = { "Order" },
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = GetTokenDTO.class))
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
            ),
        }
    )
    public Response putAction(
        @RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = PutOrderStatusDTO.class))
        ) @Valid PutOrderStatusDTO putOrderStatusDTO) throws BadRequestException, NotFoundException {

        List<Order> orders = orderRepository.findById(putOrderStatusDTO.getId());
        if(orders.size() < 1) {
            //throw new NotFoundException();
            return Response.status(404).build();
        }
        orderStatusUpdater.updateStatus(orders.get(0));
        return Response.ok().build();
    }

}
