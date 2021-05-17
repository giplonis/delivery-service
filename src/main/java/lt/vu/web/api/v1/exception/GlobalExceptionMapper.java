package lt.vu.web.api.v1.exception;

import lt.vu.infrastructure.logger.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(Exception e) {
        this.logger.error(e);

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(ExceptionDTO.create(new Exception("Unexpected error occurred")))
                .build();
    }
}
