package lt.vu.web.api.v1.exception.security;

import lt.vu.application.security.exception.TokenInvalidException;
import lt.vu.infrastructure.logger.Logger;
import lt.vu.web.api.v1.exception.ExceptionDTO;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TokenInvalidExceptionMapper implements ExceptionMapper<TokenInvalidException> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(TokenInvalidException e) {
        this.logger.error(e);

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(ExceptionDTO.create(e, Response.Status.BAD_REQUEST))
                .build();
    }
}
