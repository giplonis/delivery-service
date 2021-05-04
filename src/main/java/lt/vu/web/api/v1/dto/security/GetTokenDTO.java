package lt.vu.web.api.v1.dto.security;

import lombok.Getter;
import lombok.Setter;
import lt.vu.application.security.model.Token;

@Getter @Setter
public class GetTokenDTO {

    private String token;

    public static GetTokenDTO createFromModel(Token token) {
        GetTokenDTO dto = new GetTokenDTO();

        dto.setToken(token.getValue());

        return dto;
    }
}
