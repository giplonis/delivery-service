package lt.vu.web.api.v1.dto.userInfo;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.UserInfo;
import lt.vu.web.api.v1.dto.address.GetAddressDTO;

@Getter @Setter
public class GetUserInfoDTO {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private GetAddressDTO address;

    public static GetUserInfoDTO createFromEntity(UserInfo userInfo) {
        GetUserInfoDTO dto = new GetUserInfoDTO();

        dto.setId(userInfo.getId());
        dto.setFirstName(userInfo.getFirstName());
        dto.setLastName(userInfo.getLastName());
        dto.setEmail(userInfo.getEmail());
        dto.setPhoneNumber(userInfo.getPhoneNumber());
        dto.setAddress(GetAddressDTO.createFromEntity(userInfo.getAddress()));

        return dto;
    }
}
