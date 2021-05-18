package lt.vu.web.api.v1.admin.dto.user;

import lombok.Getter;
import lombok.Setter;
import lt.vu.web.api.v1.dto.address.GetAddressDTO;

import java.util.Date;

@Getter @Setter
public class GetEnhancedUserDTO {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private GetAddressDTO address;

    private int totalOrders;

    private Date lastOrderDate;

    private Date lastLoginDate;
}
