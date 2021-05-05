package lt.vu.web.api.v1.exception;

import lt.vu.application.exception.NotFoundException;
import lt.vu.infrastructure.logger.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(NotFoundException e) {
        this.logger.error(e);

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(ExceptionDTO.create(e, Response.Status.NOT_FOUND))
                .build();
    }
}
