package lt.vu.web.api.v1.dto.security;

import lombok.Getter;
import lombok.Setter;
import lt.vu.infrastructure.validators.PasswordsMatch;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

@Getter @Setter
@PasswordsMatch
public class PutPasswordDTO implements PasswordDTO {

    @NotNull
    @FormParam("oldPassword")
    private String oldPassword;

    @NotNull
    @Size(min = 8)
    @FormParam("password")
    private String password;

    @NotNull
    @Size(min = 8)
    @FormParam("passwordConfirm")
    private String passwordConfirm;
}
