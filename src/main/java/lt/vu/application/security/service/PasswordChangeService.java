package lt.vu.application.security.service;

import lt.vu.application.security.exception.PasswordIncorrectException;
import lt.vu.persistence.orm.entities.User;
import lt.vu.persistence.orm.repository.UserRepository;
import lt.vu.web.api.v1.dto.security.PutPasswordDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class PasswordChangeService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private PasswordHasher passwordHasher;

    @Inject
    private PasswordVerificator passwordVerificator;

    public void changePassword(User user, PutPasswordDTO passwordDTO) throws PasswordIncorrectException {
        this.passwordVerificator.verify(user, passwordDTO.getOldPassword());
        user.setPassword(this.passwordHasher.hash(passwordDTO.getPassword()));
        this.userRepository.update(user);
    }
}
