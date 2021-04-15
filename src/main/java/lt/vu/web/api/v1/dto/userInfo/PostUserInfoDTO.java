package lt.vu.web.api.v1.dto.userInfo;

import lombok.Getter;
import lombok.Setter;
import lt.vu.web.api.v1.dto.address.PostAddressDTO;

@Getter @Setter
public class PostUserInfoDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private PostAddressDTO address;
}
