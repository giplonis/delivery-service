package lt.vu.infrastructure.security;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.TextCodec;
import lombok.SneakyThrows;
import lt.vu.application.security.config.Claims;
import lt.vu.application.security.config.SecurityConfig;
import lt.vu.application.security.exception.AccessForbiddenException;
import lt.vu.application.security.exception.CredentialsMissingException;
import lt.vu.application.security.exception.TokenValidationFailedException;
import lt.vu.application.security.exception.TokenInvalidException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@Provider
@Authorized
public class AuthorizedRequestFilter implements ContainerRequestFilter {

    @Inject
    @LoggedInUser
    private CurrentUserProducer currentUserProducer;

    @Context
    private ResourceInfo resourceInfo;

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
            String token = authHeader.split(" ")[1];
            this.assertRoles(token);

            int userId = (int) this.parseToken(token).get(Claims.USER_ID);
            this.currentUserProducer.handleAuthenticationEvent(userId);
        } catch (io.jsonwebtoken.JwtException e) {
            throw new TokenValidationFailedException(e.getMessage());
        }
    }

    /**
     * Checks if token contains required role specified inside @Authorized annotation.
     */
    private void assertRoles(String token) throws AccessForbiddenException {
        Authorized authorizedAnnotation = this.resourceInfo.getResourceMethod().getAnnotation(Authorized.class);
        String requiredRole = authorizedAnnotation.role();

        List<String> tokenRoles = (List<String>) this.parseToken(token).get(Claims.ROLES);

        if (!tokenRoles.contains(requiredRole)) {
            throw new AccessForbiddenException();
        }
    }

    private <H extends Header, T> io.jsonwebtoken.Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(TextCodec.BASE64.decode(SecurityConfig.SECRET))
                .parseClaimsJws(token).getBody();
    }
}
