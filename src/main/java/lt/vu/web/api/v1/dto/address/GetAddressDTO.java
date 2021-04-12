package lt.vu.web.api.v1.dto.address;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.Address;

@Getter @Setter
public class GetAddressDTO {

    private int id;

    private String city;

    private String street;

    public static GetAddressDTO createFromEntity(Address address) {
        GetAddressDTO dto = new GetAddressDTO();

        dto.setId(address.getId());
        dto.setCity(address.getCity());
        dto.setStreet(address.getStreet());

        return dto;
    }
}
