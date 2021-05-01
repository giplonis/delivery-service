package lt.vu.infrastructure.security;

import lombok.SneakyThrows;
import lt.vu.persistence.orm.entities.User;
import lt.vu.persistence.orm.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Allows currently logged in User object to be injected inside CurrentUserAwareController
 */
@RequestScoped
public class CurrentUserProducer {

    @Produces
    @LoggedInUser
    private User user;

    @Inject
    private UserRepository userRepository;

    @SneakyThrows
    public void handleAuthenticationEvent(@Observes @LoggedInUser int userId) {
        this.user = this.userRepository.findOneById(userId);
    }
}
