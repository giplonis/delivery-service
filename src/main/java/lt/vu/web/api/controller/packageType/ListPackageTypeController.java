package lt.vu.web.api.controller.packageType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lt.vu.persistence.orm.entities.PackageType;
import lt.vu.persistence.orm.repository.PackageTypeRepository;
import lt.vu.web.api.dto.packageType.ListPackageTypeDTO;
import lt.vu.web.api.dto.packageType.PackageTypeDTO;

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
@Api(value= "ListPackageTypeController")
public class ListPackageTypeController {

    @Inject
    private PackageTypeRepository packageTypeRepository;

    @GET
    @ApiOperation(
        value = "Get valid package types",
        response = ListPackageTypeDTO.class
    )
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAction() {
        List<PackageType> packageTypes = this.packageTypeRepository.findAll();

        return Response
                .ok(new ListPackageTypeDTO(PackageTypeDTO.createMany(packageTypes)))
                .build();
    }
}
