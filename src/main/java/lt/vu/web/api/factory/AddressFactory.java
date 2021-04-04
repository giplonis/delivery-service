package lt.vu.web.api.factory;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.Address;
import lt.vu.web.api.dto.postDtos.order.AddressDTO;
import lt.vu.web.api.dto.postDtos.order.SenderDTO;

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
