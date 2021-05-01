package lt.vu.infrastructure.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.TextCodec;
import lombok.SneakyThrows;
import lt.vu.application.security.config.Claims;
import lt.vu.application.security.config.SecurityConfig;
import lt.vu.application.security.exception.CredentialsMissingException;
import lt.vu.application.security.exception.TokenValidationFailedException;
import lt.vu.application.security.exception.TokenInvalidException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Authorized
public class AuthorizedRequestFilter implements ContainerRequestFilter {

    @Inject
    @LoggedInUser
    private CurrentUserProducer currentUserProducer;

    @Override
    @SneakyThrows
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String authHeader = containerRequestContext.getHeaders().getFirst("Authorization");

        if (authHeader == null || authHeader.isEmpty()) {
            throw new CredentialsMissingException();
        }

        if (authHeader.split(" ").length < 2) {
            throw new TokenInvalidException();
        }

        try {
            int userId = (int) Jwts.parser()
                    .setSigningKey(TextCodec.BASE64.decode(SecurityConfig.SECRET))
                    .parseClaimsJws(authHeader.split(" ")[1]).getBody().get(Claims.USER_ID);

            this.currentUserProducer.handleAuthenticationEvent(userId);
        } catch (io.jsonwebtoken.JwtException e) {
            throw new TokenValidationFailedException(e.getMessage());
        }
    }
}
