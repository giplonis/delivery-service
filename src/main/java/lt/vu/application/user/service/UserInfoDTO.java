package lt.vu.application.user.service;

import lt.vu.web.api.v1.dto.address.PostAddressDTO;

public interface UserInfoDTO {

    String getFirstName();

    String getLastName();

    String getEmail();

    String getPhoneNumber();

    PostAddressDTO getAddress();
}
