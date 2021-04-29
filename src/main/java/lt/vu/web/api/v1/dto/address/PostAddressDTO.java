package lt.vu.web.api.v1.dto.address;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

@Getter @Setter
public class PostAddressDTO {

    @NotNull
    @FormParam("city")
    @Size(max = 30)
    private String city;

    @NotNull
    @Size(max = 50)
    @FormParam("street")
    private String street;
}
