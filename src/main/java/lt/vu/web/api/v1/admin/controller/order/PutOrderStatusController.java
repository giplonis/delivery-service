package lt.vu.web.api.v1.admin.controller.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.vu.application.exception.BadRequestException;
import lt.vu.application.exception.NotFoundException;
import lt.vu.infrastructure.security.Authorized;
import lt.vu.persistence.entities.Order;
import lt.vu.persistence.entities.OrderStatus;
import lt.vu.persistence.entities.UserRole;
import lt.vu.persistence.repository.OrderRepository;
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

@Path("/admin/orders")
@RequestScoped
public class PutOrderStatusController {

    @Inject
    private OrderRepository orderRepository;

    @PUT
    @Path("/{id}/status")
    @Authorized(role = UserRole.ADMIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Operation(
        summary = "Change order's status",
        description = "Changes order status to given.",
        tags = { "Admin-Order" },
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

        Order order = this.orderRepository.findOneById(orderId);
        order.setStatus(OrderStatus.valueOf(putOrderStatusDTO.getStatus()));
        this.orderRepository.update(order);

        return Response
                .ok(GetOrderDTO.createFromEntity(order))
                .build();
    }
}
