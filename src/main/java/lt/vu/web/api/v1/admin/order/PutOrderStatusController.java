package lt.vu.web.api.v1.admin.order;

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
import lt.vu.persistence.orm.entities.OrderStatus;
import lt.vu.persistence.orm.repository.OrderRepository;
import lt.vu.web.api.v1.controller.security.CurrentUserAwareController;
import lt.vu.web.api.v1.admin.dto.order.PutOrderStatusDTO;
import lt.vu.web.api.v1.dto.order.GetOrderDTO;
import lt.vu.web.api.v1.dto.security.GetTokenDTO;
import lt.vu.web.api.v1.exception.ExceptionDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin/orders/{id}/change-status")
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
        ) @Valid PutOrderStatusDTO putOrderStatusDTO,
        @PathParam("id") Integer orderId) throws BadRequestException, NotFoundException {

        Order order = orderRepository.findById(orderId);
        orderStatusUpdater.forceStatus(order, OrderStatus.valueOf(putOrderStatusDTO.getOrderStatus()));
        order = orderRepository.findById(order.getId());
        return Response.ok(GetOrderDTO.createFromEntity(order)).build();
    }
}
