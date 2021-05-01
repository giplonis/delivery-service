package lt.vu.application.security.service;

import lt.vu.application.security.exception.AuthenticationFailedException;
import lt.vu.application.security.model.Token;
import lt.vu.persistence.orm.entities.User;
import lt.vu.persistence.orm.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AuthenticationService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private PasswordVerificator passwordVerificator;

    @Inject
    private JWTBuilder jwtBuilder;

    public Token login(String email, String password) throws AuthenticationFailedException {
        User user = this.userRepository.findOneByEmail(email);

        this.passwordVerificator.verify(user, password);

        return jwtBuilder.build(user);
    }
}
