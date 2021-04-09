package lt.vu.web.api.v1.controller.packageType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.vu.persistence.orm.entities.PackageType;
import lt.vu.persistence.orm.repository.PackageTypeRepository;
import lt.vu.web.api.v1.dto.getDto.packageType.ListPackageTypeDTO;
import lt.vu.web.api.v1.dto.getDto.packageType.PackageTypeDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/package-type")
@RequestScoped
public class ListPackageTypeController {

    @Inject
    private PackageTypeRepository packageTypeRepository;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Fetch list of package types available",
        tags = { "PackageType" },
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = ListPackageTypeDTO.class))
            )
        }
    )
    public Response listAction() {
        List<PackageType> packageTypes = this.packageTypeRepository.findAll();

        return Response
                .ok(new ListPackageTypeDTO(PackageTypeDTO.createMany(packageTypes)))
                .build();
    }
}
