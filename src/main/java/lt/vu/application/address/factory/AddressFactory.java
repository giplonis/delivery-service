package lt.vu.application.address.factory;

import lt.vu.persistence.entities.Address;
import lt.vu.web.api.v1.dto.address.PostAddressDTO;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class AddressFactory {

    public Address createFromDTO(PostAddressDTO addressDTO) {
        Address address = new Address();

        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());

        return address;
    }
}
