package lt.vu.web.api.v1.controller.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.vu.application.exception.NotFoundException;
import lt.vu.application.security.exception.AuthenticationFailedException;
import lt.vu.application.security.model.Token;
import lt.vu.application.security.service.AuthenticationService;
import lt.vu.web.api.v1.dto.security.GetTokenDTO;
import lt.vu.web.api.v1.dto.security.PostRegisterDTO;
import lt.vu.web.api.v1.exception.ExceptionDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/register")
@RequestScoped
public class RegisterController {

    @Inject
    private AuthenticationService authenticationService;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Operation(
        summary = "Register new user",
        tags = { "Security" },
        responses = {
            @ApiResponse(
                responseCode = "201",
                content = @Content(schema = @Schema(implementation = GetTokenDTO.class))
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
    public Response registerAction(
        @RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = PostRegisterDTO.class))
        ) @Valid PostRegisterDTO postRegisterDTO)
            throws AuthenticationFailedException, NotFoundException {

        Token token = this.authenticationService.register(postRegisterDTO);

        return Response
                .status(Response.Status.CREATED)
                .entity(GetTokenDTO.createFromModel(token))
                .build();
    }
}
