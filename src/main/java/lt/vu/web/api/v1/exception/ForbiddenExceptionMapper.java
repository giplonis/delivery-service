package lt.vu.web.api.v1.exception;

import lt.vu.application.exception.ForbiddenException;
import lt.vu.infrastructure.logger.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(ForbiddenException e) {
        this.logger.error(e);

        return Response
                .status(Response.Status.FORBIDDEN)
                .entity(ExceptionDTO.create(e, Response.Status.FORBIDDEN))
                .build();
    }
}
