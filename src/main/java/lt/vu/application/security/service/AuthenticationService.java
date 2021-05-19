package lt.vu.application.security.service;

import lombok.SneakyThrows;
import lt.vu.application.exception.BadRequestException;
import lt.vu.application.exception.NotFoundException;
import lt.vu.application.security.model.Token;
import lt.vu.application.user.exception.UserAlreadyExistsException;
import lt.vu.application.user.factory.UserFactory;
import lt.vu.persistence.orm.entities.User;
import lt.vu.persistence.orm.repository.UserRepository;
import lt.vu.web.api.v1.dto.security.PostRegisterDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;

@ApplicationScoped
public class AuthenticationService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private PasswordVerificator passwordVerificator;

    @Inject
    private UserFactory userFactory;

    @Inject
    private JWTBuilder jwtBuilder;

    public Token login(String email, String password)
            throws BadRequestException, NotFoundException {
        User user = this.userRepository.findOneByEmail(email);

        this.passwordVerificator.verify(user, password);

        this.updateLastLoginDate(user);

        return jwtBuilder.build(user);
    }

    @SneakyThrows
    public Token register(PostRegisterDTO registerDTO) {
        this.verifyNewUser(registerDTO);

        User user = this.userFactory.create(registerDTO);
        this.userRepository.persist(user);

        return this.login(registerDTO.getEmail(), registerDTO.getPassword());
    }

    /**
     * Checks if user with provided email does not already exist
     */
    private void verifyNewUser(PostRegisterDTO registerDTO) throws UserAlreadyExistsException {
        try {
            this.userRepository.findOneByEmail(registerDTO.getEmail());

            throw new UserAlreadyExistsException();
        } catch (NotFoundException e) {
            // User does not exist - all good
        }
    }

    private void updateLastLoginDate(User user) {
        user.setLastLogin(new Date());
        this.userRepository.persist(user);
    }
}
