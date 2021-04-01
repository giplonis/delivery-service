package lt.vu.web.api.controller.packageType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lt.vu.persistence.orm.entities.PackageType;
import lt.vu.persistence.orm.repository.PackageTypeRepository;
import lt.vu.web.api.controller.AbstractApiController;
import lt.vu.web.api.dto.PackageTypeDTO;

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
@Api(value= "List package type ") // swagger minimum (controller name)
public class ListPackageTypeController extends AbstractApiController<List<PackageTypeDTO>> {

    @Inject
    private PackageTypeRepository packageTypeRepository;

    @GET
    @ApiOperation(value = "Retrieves valid package types",         //swagger optional (endpoint summary)
                notes = "Return some json to the client")  //swagger optional (description)
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAction() {
        List<PackageType> packageTypes = this.packageTypeRepository.findAll();

        return Response
                .ok(this.getApiResponse(PackageTypeDTO.createMany(packageTypes)))
                .build();
    }
}
