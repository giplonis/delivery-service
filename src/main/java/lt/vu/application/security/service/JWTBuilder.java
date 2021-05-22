package lt.vu.application.security.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import lt.vu.application.security.config.SecurityConfig;
import lt.vu.application.security.config.Claims;
import lt.vu.application.security.model.Token;
import lt.vu.persistence.orm.entities.User;

import javax.enterprise.context.RequestScoped;
import java.util.Date;

@RequestScoped
public class JWTBuilder {

    public Token build(User user) {
        String jwt = Jwts.builder()
                .claim(Claims.USER_ID, user.getId())
                .claim(Claims.ROLES, user.getRoles())
                .setIssuedAt(new Date())
                .setIssuer(Claims.ISSUER)
                // Expires after 30 minutes
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .signWith(
                    SignatureAlgorithm.HS256,
                    TextCodec.BASE64.decode(SecurityConfig.SECRET)
                )
                .compact();

        return new Token(jwt);
    }
}
