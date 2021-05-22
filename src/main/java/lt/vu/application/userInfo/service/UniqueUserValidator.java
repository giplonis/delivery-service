package lt.vu.application.userInfo.service;

import lt.vu.application.exception.NotFoundException;
import lt.vu.application.user.exception.UserAlreadyExistsException;
import lt.vu.persistence.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UniqueUserValidator {

    @Inject
    UserRepository userRepository;

    public void validate(String email) throws UserAlreadyExistsException {
        try {
            this.userRepository.findOneByEmail(email);

            throw new UserAlreadyExistsException();
        } catch (NotFoundException e) {
            // User does not exist - all good
        }
    }
}
