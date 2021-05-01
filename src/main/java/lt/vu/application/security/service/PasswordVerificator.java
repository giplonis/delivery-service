package lt.vu.application.security.service;

import lt.vu.application.security.exception.PasswordIncorrectException;
import lt.vu.persistence.orm.entities.User;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PasswordVerificator {

    public void verify(User user, String password) throws PasswordIncorrectException {
        if (!user.getPassword().equals(password)) {
            throw new PasswordIncorrectException();
        }
    }
}
