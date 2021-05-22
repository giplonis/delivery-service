package lt.vu.application.userInfo.service;

import lombok.SneakyThrows;
import lt.vu.application.address.factory.AddressFactory;
import lt.vu.persistence.entities.User;
import lt.vu.persistence.repository.UserRepository;
import lt.vu.web.api.v1.dto.userInfo.UserInfoDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserInfoUpdateService {

    @Inject
    private AddressFactory addressFactory;

    @Inject
    private UserRepository userRepository;

    @Inject
    private UniqueUserValidator uniqueUserValidator;

    @SneakyThrows
    public void updateUser(User user, UserInfoDTO userInfoDTO) {
        this.uniqueUserValidator.validate(userInfoDTO.getEmail());

        user.setEmail(userInfoDTO.getEmail());
        user.setFirstName(userInfoDTO.getFirstName());
        user.setLastName(userInfoDTO.getLastName());
        user.setPhoneNumber(userInfoDTO.getPhoneNumber());
        user.setAddress(this.addressFactory.createFromDTO(userInfoDTO.getAddress()));

        this.userRepository.update(user);
    }
}
