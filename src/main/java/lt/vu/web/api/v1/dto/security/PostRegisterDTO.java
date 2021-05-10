package lt.vu.web.api.v1.dto.security;

import lombok.Getter;
import lombok.Setter;
import lt.vu.infrastructure.validators.PasswordsMatch;
import lt.vu.infrastructure.validators.PhoneNumber;
import lt.vu.web.api.v1.dto.address.PostAddressDTO;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

@Getter @Setter
@PasswordsMatch
public class PostRegisterDTO implements PasswordDTO {

    @NotNull
    @Email
    @FormParam("email")
    private String email;

    @NotNull
    @Size(min = 8)
    @FormParam("password")
    private String password;

    @NotNull
    @Size(min = 8)
    @FormParam("passwordConfirm")
    private String passwordConfirm;

    @Size(max = 50)
    @NotNull
    @FormParam("firstName")
    private String firstName;

    @Size(max = 50)
    @NotNull
    @FormParam("lastName")
    private String lastName;

    @NotNull
    @PhoneNumber
    @FormParam("phoneNumber")
    private String phoneNumber;

    @NotNull
    @FormParam("address")
    @Valid
    private PostAddressDTO address;

}
