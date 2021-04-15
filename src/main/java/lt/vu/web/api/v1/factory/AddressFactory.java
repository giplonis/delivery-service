package lt.vu.web.api.v1.factory;

import lt.vu.persistence.orm.entities.Address;
import lt.vu.web.api.v1.dto.address.PostAddressDTO;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class AddressFactory {

    public Address create(PostAddressDTO addressDTO) {
        Address address = new Address();

        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());

        return address;
    }
}
