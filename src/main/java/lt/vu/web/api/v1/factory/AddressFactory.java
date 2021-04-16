package lt.vu.web.api.v1.factory;

import lt.vu.persistence.orm.entities.Address;
import lt.vu.web.api.v1.dto.address.PostAddressDTO;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class AddressFactory {

    public Address create(PostAddressDTO addressDTO) {
        Address address = new Address();
        validate(addressDTO);

        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());

        return address;
    }

    private void validate(PostAddressDTO addressDTO){
         if(!addressDTO.getCity().matches("^[ A-Za-z]{2,30}"))
             throw new IllegalArgumentException("Wrong city.");

        if(!addressDTO.getStreet().matches("^[ A-Za-z/0-9.]{2,30}"))
            throw new IllegalArgumentException("Wrong street.");
    }
}
