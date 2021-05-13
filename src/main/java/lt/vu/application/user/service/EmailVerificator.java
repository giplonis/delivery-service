package lt.vu.application.user.service;

import lt.vu.application.exception.NotFoundException;
import lt.vu.application.user.exception.UserAlreadyExistsException;
import lt.vu.persistence.orm.entities.User;
import lt.vu.persistence.orm.repository.UserInfoRepository;
import lt.vu.persistence.orm.repository.UserRepository;
import lt.vu.web.api.v1.dto.security.PostRegisterDTO;

import javax.inject.Inject;

public class EmailVerificator {

    @Inject
    UserRepository userRepository;

    public void verify(String email) throws UserAlreadyExistsException {
        try {
            this.userRepository.findOneByEmail(email);

            throw new UserAlreadyExistsException();
        } catch (NotFoundException e) {
            // User does not exist - all good
        }
    }
}
