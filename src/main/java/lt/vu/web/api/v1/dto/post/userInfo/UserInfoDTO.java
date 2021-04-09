package lt.vu.web.api.v1.dto.post.userInfo;

import lombok.Getter;
import lombok.Setter;
import lt.vu.web.api.v1.dto.post.address.AddressDTO;

@Getter @Setter
public class UserInfoDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private AddressDTO address;
}
