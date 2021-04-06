package lt.vu.web.api.v1.dto.getDto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiResourceDTO<T> {

    private T data;

    public ApiResourceDTO(T data) {
        this.data = data;
    }
}
