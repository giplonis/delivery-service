package lt.vu.web.api.v1.factory;

import lt.vu.persistence.orm.entities.UserInfo;
import lt.vu.persistence.orm.repository.UserInfoRepository;
import lt.vu.web.api.v1.dto.userInfo.PostUserInfoDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;

@RequestScoped
public class UserInfoFactory {

    @Inject
    private AddressFactory addressFactory;

    public UserInfo create(PostUserInfoDTO userData) {
        UserInfo userInfo = new UserInfo();
        validate(userData);

        userInfo.setFirstName(userData.getFirstName());
        userInfo.setLastName(userData.getLastName());
        userInfo.setEmail(userData.getEmail());
        userInfo.setPhoneNumber(userData.getPhoneNumber());
        userInfo.setAddress(addressFactory.create(userData.getAddress()));

        return userInfo;
    }

    private void validate(PostUserInfoDTO userData){
        if(!userData.getFirstName().chars().allMatch(Character::isLetter))
            throw new IllegalArgumentException("Name should only consist of letter symbols. Provide only first name");

        if(!userData.getLastName().chars().allMatch(Character::isLetter))
            throw new IllegalArgumentException("Last name should only consist of letter symbols");

        if(!userData.getEmail().matches("^(.+)@(.+)$"))
            throw new IllegalArgumentException("Wrong email format.");

        if(!userData.getPhoneNumber().matches("^[0-9]{8}$"))
            throw new IllegalArgumentException("Wrong phone number.");
    }
}
