package lt.vu.application.security.model;

import lombok.Getter;

@Getter
public class Token {

    private final String value;

    public Token(String value) {
        this.value = value;
    }
}
