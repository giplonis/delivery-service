package lt.vu.web.api.v1.exception.security;

import lt.vu.application.security.exception.UnauthorizedException;
import lt.vu.infrastructure.logger.Logger;
import lt.vu.web.api.v1.exception.ExceptionDTO;

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
