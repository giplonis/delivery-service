package lt.vu.web.api.v1.dto.get.user;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.User;
import lt.vu.web.api.v1.dto.get.address.AddressDTO;

@Getter @Setter
public class UserDTO {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private AddressDTO address;

    public static UserDTO createFromEntity(User user) {
        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setAddress(AddressDTO.createFromEntity(user.getAddress()));

        return dto;
    }
}
