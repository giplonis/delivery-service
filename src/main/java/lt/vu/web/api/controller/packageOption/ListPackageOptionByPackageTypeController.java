package lt.vu.web.api.controller.packageOption;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lt.vu.persistence.orm.entities.PackageOption;
import lt.vu.persistence.orm.entities.PackageType;
import lt.vu.persistence.orm.repository.PackageOptionRepository;
import lt.vu.web.api.controller.AbstractApiController;
import lt.vu.web.api.dto.PackageOptionDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/package-option")
@RequestScoped
@Api(value= "List package size ")


public class ListPackageOptionByPackageTypeController extends AbstractApiController<List<PackageOptionDTO>> {

    @Inject
    private PackageOptionRepository packageOptionRepository;


    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response listAction(@QueryParam("packageTypeId") int packageIntId){ //String packageTypeIdString) {
        List<PackageOption> packageOptions = this.packageOptionRepository.findByType(packageIntId);
        return Response
                .ok(this.getApiResponse(PackageOptionDTO.createMany(packageOptions)))
                .build();
    }

}
