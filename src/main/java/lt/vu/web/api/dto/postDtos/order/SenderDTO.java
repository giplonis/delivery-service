package lt.vu.web.api.dto.postDtos.order;

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
