package lt.vu.web.api.dto.getDtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiResourceDTO<T> {

    private T data;

    public ApiResourceDTO(T data) {
        this.data = data;
    }
}
