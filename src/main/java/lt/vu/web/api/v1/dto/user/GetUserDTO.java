package lt.vu.web.api.v1.dto.user;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.User;
import lt.vu.web.api.v1.dto.address.GetAddressDTO;

@Getter @Setter
public class GetUserDTO {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private GetAddressDTO address;

    public static GetUserDTO createFromEntity(User user) {
        GetUserDTO dto = new GetUserDTO();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setAddress(GetAddressDTO.createFromEntity(user.getAddress()));

        return dto;
    }
}
