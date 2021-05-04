package lt.vu.web.api.v1.exception;

import lt.vu.application.exception.UnauthorizedException;
import lt.vu.infrastructure.logger.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnauthorizedExceptionMapper implements ExceptionMapper<UnauthorizedException> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(UnauthorizedException e) {
        this.logger.error(e);

        return Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(ExceptionDTO.create(e, Response.Status.UNAUTHORIZED))
                .build();
    }
}
