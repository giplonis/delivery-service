package lt.vu.web.api.v1.exception;

import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.core.Response;

@Getter @Setter
public class ExceptionDTO {

    private int statusCode;

    private String message;

    public static ExceptionDTO create(Exception e) {
        ExceptionDTO dto = new ExceptionDTO();

        dto.setMessage(e.getMessage());
        dto.setStatusCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());

        return dto;
    }

    public static ExceptionDTO create(Exception e, Response.Status status) {
        ExceptionDTO dto = new ExceptionDTO();

        dto.setMessage(e.getMessage());
        dto.setStatusCode(status.getStatusCode());

        return dto;
    }
}
