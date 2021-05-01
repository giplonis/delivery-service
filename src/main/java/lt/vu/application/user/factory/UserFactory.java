package lt.vu.application.user.factory;

import lt.vu.persistence.orm.entities.User;
import lt.vu.web.api.v1.dto.security.PostRegisterDTO;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserFactory {

    public User create(PostRegisterDTO registerDTO) {
        User user = new User();

        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());

        return user;
    }
}
