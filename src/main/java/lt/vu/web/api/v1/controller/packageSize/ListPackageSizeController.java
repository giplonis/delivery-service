package lt.vu.web.api.v1.controller.packageSize;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.vu.persistence.orm.entities.PackageSize;
import lt.vu.persistence.orm.repository.PackageSizeRepository;
import lt.vu.web.api.v1.dto.get.packageSize.ListPackageSizeDTO;
import lt.vu.web.api.v1.dto.get.packageSize.PackageSizeDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/package-sizes")
@RequestScoped
public class ListPackageSizeController {

    @Inject
    private PackageSizeRepository packageSizeRepository;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Fetch list of package sizes available",
        tags = { "PackageSize" },
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = ListPackageSizeDTO.class))
            )
        }
    )
    public Response listAction() {
        List<PackageSize> packageSizes = this.packageSizeRepository.findAll();

        return Response
                .ok(new ListPackageSizeDTO(PackageSizeDTO.createMany(packageSizes)))
                .build();
    }
}
