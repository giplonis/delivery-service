package lt.vu.application.security.service;

import lt.vu.application.security.exception.PasswordIncorrectException;
import lt.vu.persistence.orm.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PasswordVerificator {

    @Inject
    private PasswordHasher passwordHasher;

    public void verify(User user, String password) throws PasswordIncorrectException {
        if (!user.getPassword().equals(this.passwordHasher.hash(password))) {
            throw new PasswordIncorrectException();
        }
    }
}
