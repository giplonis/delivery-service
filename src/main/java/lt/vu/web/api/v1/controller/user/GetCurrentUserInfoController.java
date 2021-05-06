package lt.vu.web.api.v1.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.vu.infrastructure.security.Authorized;
import lt.vu.web.api.v1.controller.security.CurrentUserAwareController;
import lt.vu.web.api.v1.dto.user.GetUserDTO;
import lt.vu.web.api.v1.exception.ExceptionDTO;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/current-user")
@RequestScoped
public class GetCurrentUserInfoController extends CurrentUserAwareController {

    @GET
    @Path("/")
    @Authorized
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Fetch data of user",
        tags = { "User" },
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = GetUserDTO.class))
            ),
            @ApiResponse(
                responseCode = "500",
                content = @Content(schema = @Schema(implementation = ExceptionDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(schema = @Schema(implementation = ExceptionDTO.class))
            )
        }
    )
    public Response getAction() {
        return Response
                .ok(GetUserDTO.createFromEntity(this.user))
                .build();
    }
}
