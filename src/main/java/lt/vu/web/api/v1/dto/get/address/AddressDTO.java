package lt.vu.web.api.v1.dto.get.address;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.Address;

@Getter @Setter
public class AddressDTO {

    private int id;

    private String city;

    private String street;

    public static AddressDTO createFromEntity(Address address) {
        AddressDTO dto = new AddressDTO();

        dto.setId(address.getId());
        dto.setCity(address.getCity());
        dto.setStreet(address.getStreet());

        return dto;
    }
}
