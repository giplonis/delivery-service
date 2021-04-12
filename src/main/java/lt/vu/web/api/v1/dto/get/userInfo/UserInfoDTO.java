package lt.vu.web.api.v1.dto.get.userInfo;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.UserInfo;
import lt.vu.web.api.v1.dto.get.address.AddressDTO;

@Getter @Setter
public class UserInfoDTO {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private AddressDTO address;

    public static UserInfoDTO createFromEntity(UserInfo userInfo) {
        UserInfoDTO dto = new UserInfoDTO();

        dto.setId(userInfo.getId());
        dto.setFirstName(userInfo.getFirstName());
        dto.setLastName(userInfo.getLastName());
        dto.setEmail(userInfo.getEmail());
        dto.setPhoneNumber(userInfo.getPhoneNumber());
        dto.setAddress(AddressDTO.createFromEntity(userInfo.getAddress()));

        return dto;
    }
}
