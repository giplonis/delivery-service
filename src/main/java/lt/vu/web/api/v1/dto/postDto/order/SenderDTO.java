package lt.vu.web.api.v1.dto.postDto.order;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SenderDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private AddressDTO address;
}
