package lt.vu.web.api.v1.controller.packageOption;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.vu.persistence.orm.entities.PackageOption;
import lt.vu.persistence.orm.repository.PackageOptionRepository;
import lt.vu.web.api.v1.dto.packageOption.ListPackageOptionDTO;
import lt.vu.web.api.v1.dto.packageOption.GetPackageOptionDTO;
import lt.vu.web.api.v1.exception.ExceptionDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/package-options")
@RequestScoped
public class ListPackageOptionByPackageTypeController {

    @Inject
    private PackageOptionRepository packageOptionRepository;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Fetch list of package options",
        tags = { "PackageOption" },
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = ListPackageOptionDTO.class))
            ),
            @ApiResponse(
                responseCode = "500",
                content = @Content(schema = @Schema(implementation = ExceptionDTO.class))
            )
        }
    )
    public Response listAction(@QueryParam("packageTypeId") Integer packageTypeId) {
        List<PackageOption> packageOptions = this.getPackageOptions(packageTypeId);

        return Response
                .ok(new ListPackageOptionDTO(GetPackageOptionDTO.createMany(packageOptions)))
                .build();
    }

    private List<PackageOption> getPackageOptions(Integer packageTypeId) {
        if (packageTypeId == null) {
            return this.packageOptionRepository.findAll();
        }

        return this.packageOptionRepository.findByPackageType(packageTypeId);
    }
}
