package lt.vu.application.user.service;

import lt.vu.application.address.factory.AddressFactory;
import lt.vu.application.user.exception.UserAlreadyExistsException;
import lt.vu.persistence.orm.entities.User;
import lt.vu.persistence.orm.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserInfoUpdateService {
    @Inject
    private AddressFactory addressFactory;

    @Inject
    private UserRepository userRepository;

    @Inject
    private EmailVerificator emailVerificator;

    public void updateUser(User user, UserInfoDTO userInfoDTO) throws UserAlreadyExistsException {
        this.emailVerificator.verify(userInfoDTO.getEmail());

        user.setEmail(userInfoDTO.getEmail());
        user.setFirstName(userInfoDTO.getFirstName());
        user.setLastName(userInfoDTO.getLastName());
        user.setPhoneNumber(userInfoDTO.getPhoneNumber());
        user.setAddress(this.addressFactory.createFromDTO(userInfoDTO.getAddress()));

        this.userRepository.update(user);
    }
}
