package lt.vu.web.api.v1.factory;

import lt.vu.persistence.orm.entities.Address;
import lt.vu.web.api.v1.dto.postDto.order.AddressDTO;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class AddressFactory {

    public Address create(AddressDTO addressDTO){
        Address address = new Address();
        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());
        address.setNumber("1");
        return address;
    }
}
