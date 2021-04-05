package lt.vu.web.api.v1.factory;

import lt.vu.persistence.orm.entities.UserInfo;
import lt.vu.web.api.v1.dto.postDto.order.SenderDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class UserInfoFactory {

    @Inject
    private AddressFactory addressFactory;

    public UserInfo create(SenderDTO userData){
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(userData.getFirstName());
        userInfo.setLastName(userData.getLastName());
        userInfo.setEmail(userData.getEmail());
        userInfo.setPhoneNumber(userData.getPhoneNumber());
        userInfo.setAddress(addressFactory.create(userData.getAddress()));
        return userInfo;
    }
}
