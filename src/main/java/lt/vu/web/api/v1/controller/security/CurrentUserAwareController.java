package lt.vu.web.api.v1.controller.security;

import lt.vu.infrastructure.security.LoggedInUser;
import lt.vu.persistence.entities.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class CurrentUserAwareController {

    @Inject
    @LoggedInUser
    protected User user;
}
