package lt.vu.web.api.v1.controller.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.vu.application.exception.BadRequestException;
import lt.vu.application.exception.NotFoundException;
import lt.vu.application.security.model.Token;
import lt.vu.application.security.service.AuthenticationService;
import lt.vu.application.security.service.PasswordChangeService;
import lt.vu.infrastructure.security.Authorized;
import lt.vu.web.api.v1.dto.security.GetTokenDTO;
import lt.vu.web.api.v1.dto.security.PutPasswordDTO;
import lt.vu.web.api.v1.exception.ExceptionDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user/password")
@RequestScoped
public class ChangePasswordController extends CurrentUserAwareController {

    @Inject
    private PasswordChangeService passwordChangeService;

    @Inject
    private AuthenticationService authenticationService;

    @PUT
    @Path("/")
    @Authorized
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Operation(
        summary = "Change user's password",
        description = "Changes user's password and returns new token.",
        tags = { "Security" },
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
            content = @Content(schema = @Schema(implementation = PutPasswordDTO.class))
        ) @Valid PutPasswordDTO putPasswordDTO) throws BadRequestException, NotFoundException {

        this.passwordChangeService.changePassword(this.user, putPasswordDTO);
        Token token = this.authenticationService.login(this.user.getEmail(), putPasswordDTO.getPassword());

        return Response.ok(GetTokenDTO.createFromModel(token)).build();
    }
}