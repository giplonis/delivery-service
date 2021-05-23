package lt.vu.web.api.v1.controller.attribute;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.vu.infrastructure.interceptors.LoggedAction;
import lt.vu.persistence.orm.entities.Attribute;
import lt.vu.persistence.orm.repository.AttributeRepository;
import lt.vu.web.api.v1.dto.attribute.GetAttributeDTO;
import lt.vu.web.api.v1.dto.attribute.ListAttributeDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/attributes")
@RequestScoped
public class ListAttributeController {

    @Inject
    private AttributeRepository attributeRepository;

    @GET
    @Path("/")
    @LoggedAction
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Fetch list of available order attributes",
        tags = { "Attribute" },
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = ListAttributeDTO.class))
            )
        }
    )
    public Response listAction() {
        List<Attribute> attributes = this.attributeRepository.findAll();

        return Response
                .ok(new ListAttributeDTO(GetAttributeDTO.createMany(attributes)))
                .build();
    }
}
