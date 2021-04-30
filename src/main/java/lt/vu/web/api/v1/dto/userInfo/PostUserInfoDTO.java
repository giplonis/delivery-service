package lt.vu.web.api.v1.dto.userInfo;

import lombok.Getter;
import lombok.Setter;
import lt.vu.web.api.v1.dto.address.PostAddressDTO;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.ws.rs.FormParam;

@Getter @Setter
public class PostUserInfoDTO {

    @Size(max = 50)
    @NotNull
    @FormParam("firstName")
    private String firstName;

    @Size(max = 50)
    @NotNull
    @FormParam("lastName")
    private String lastName;

    @NotNull
    @Email
    @FormParam("email")
    private String email;

    @NotNull
    @Pattern(regexp = "^(\\+?\\d{0,4})?\\s?-?\\s?(\\(?\\d{3}\\)?)\\s?-?\\s?(\\(?\\d{3}\\)?)\\s?-?\\s?(\\(?\\d{4}\\)?)?$")
    @FormParam("phoneNumber")
    private String phoneNumber;

    @NotNull
    @FormParam("address")
    @Valid
    private PostAddressDTO address;
}
