package lt.vu.web.api.controller.packageSize;

import lt.vu.persistence.orm.entities.PackageSize;
import lt.vu.persistence.orm.repository.PackageSizeRepository;
import lt.vu.web.api.controller.AbstractApiController;
import lt.vu.web.api.dto.PackageSizeDTO;

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
public class ListPackageSizeController extends AbstractApiController<List<PackageSizeDTO>> {

    @Inject
    private PackageSizeRepository packageSizeRepository;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAction() {
        List<PackageSize> packageSizes = this.packageSizeRepository.findAll();

        return Response
                .ok(this.getApiResponse(PackageSizeDTO.createMany(packageSizes)))
                .build();
    }
}
