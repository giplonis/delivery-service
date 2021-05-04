package lt.vu.web.api.v1.dto.security;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;

@Getter @Setter
public class PostLoginDTO {

    @NotNull
    @Email
    @FormParam("email")
    private String email;

    @NotNull
    @FormParam("password")
    private String password;
}
