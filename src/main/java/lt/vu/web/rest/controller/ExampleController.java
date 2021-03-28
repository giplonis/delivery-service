package lt.vu.web.rest.controller;

import lombok.*;
import lt.vu.persistence.orm.entities.Example;
import lt.vu.persistence.orm.dao.ExampleDAO;
import lt.vu.web.rest.dto.ExampleDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/example")
public class ExampleController {

    @Inject
    @Setter @Getter
    private ExampleDAO exampleDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Example example = exampleDAO.findOne(id);
        if (example == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ExampleDTO exampleDto = new ExampleDTO();
        exampleDto.setTitle(example.getTitle());

        return Response.ok(exampleDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer id,
            ExampleDTO exampleDTO
    ) {
        try {
            Example existingExample = exampleDAO.findOne(id);

            if (existingExample == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            exampleDAO.update(existingExample);

            return Response.ok().build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
