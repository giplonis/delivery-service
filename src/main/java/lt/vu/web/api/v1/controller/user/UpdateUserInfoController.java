package lt.vu.web.api.v1.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.vu.application.exception.BadRequestException;
import lt.vu.application.exception.NotFoundException;
import lt.vu.application.security.model.Token;
import lt.vu.application.user.service.UpdateUserInfoService;
import lt.vu.infrastructure.security.Authorized;
import lt.vu.persistence.orm.repository.UserRepository;
import lt.vu.web.api.v1.controller.security.CurrentUserAwareController;
import lt.vu.web.api.v1.dto.security.GetTokenDTO;
import lt.vu.web.api.v1.dto.user.GetUserDTO;
import lt.vu.web.api.v1.dto.user.UpdateUserDTO;
import lt.vu.web.api.v1.dto.userInfo.PostUserInfoDTO;
import lt.vu.web.api.v1.exception.ExceptionDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user/details")
@RequestScoped
public class UpdateUserInfoController extends CurrentUserAwareController {

    @Inject
    private UpdateUserInfoService updateUserInfoService;

    @PUT
    @Path("/")
    @Authorized
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Operation(
            summary = "Change user's data",
            description = "Changes user's data and returns updated info.",
            tags = { "User details" },
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
                    content = @Content(schema = @Schema(implementation = UpdateUserDTO.class))
            ) @Valid UpdateUserDTO updateUserInfoDTO) throws BadRequestException, NotFoundException {

        updateUserInfoService.updateUser(this.user, updateUserInfoDTO);

        return Response
                .ok(GetUserDTO.createFromEntity(this.user))
                .build();
    }
}

