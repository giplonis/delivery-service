package lt.vu.web.api.controller.packageSize;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lt.vu.persistence.orm.entities.PackageSize;
import lt.vu.persistence.orm.repository.PackageSizeRepository;
import lt.vu.web.api.dto.packageSize.ListPackageSizeDTO;
import lt.vu.web.api.dto.packageSize.PackageSizeDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/package-size")
@RequestScoped
@Api(value= "ListPackageSizeController")
public class ListPackageSizeController {

    @Inject
    private PackageSizeRepository packageSizeRepository;

    @GET
    @ApiOperation(
        value = "Get valid package sizes",
        response = ListPackageSizeDTO.class
    )
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAction() {
        List<PackageSize> packageSizes = this.packageSizeRepository.findAll();

        return Response
                .ok(new ListPackageSizeDTO(PackageSizeDTO.createMany(packageSizes)))
                .build();
    }
}
