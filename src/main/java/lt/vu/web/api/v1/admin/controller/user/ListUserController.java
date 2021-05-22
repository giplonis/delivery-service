package lt.vu.web.api.v1.admin.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.vu.infrastructure.security.Authorized;
import lt.vu.persistence.entities.User;
import lt.vu.persistence.entities.UserRole;
import lt.vu.persistence.repository.UserRepository;
import lt.vu.web.api.v1.admin.dto.user.GetEnhancedUserDTOFactory;
import lt.vu.web.api.v1.admin.dto.user.ListEnhancedUserDTO;
import lt.vu.web.api.v1.exception.ExceptionDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/admin/users")
@RequestScoped
public class ListUserController {

    @Inject
    private UserRepository userRepository;

    @Inject
    private GetEnhancedUserDTOFactory dtoFactory;

    @GET
    @Path("/")
    @Authorized(role = UserRole.ADMIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "List of all registered users",
        tags = { "Admin-User" },
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = ListEnhancedUserDTO.class))
            ),
            @ApiResponse(
                responseCode = "500",
                content = @Content(schema = @Schema(implementation = ExceptionDTO.class))
            )
        }
    )
    public Response listAction() {
        List<User> users = this.userRepository.findByRole(UserRole.USER);

        return Response
                .ok(new ListEnhancedUserDTO(this.dtoFactory.createMany(users)))
                .build();
    }
}
