package lt.vu.web.api.v1.dto.user;

import lt.vu.web.api.v1.dto.ApiResourceDTO;

import java.util.List;

public class ListUserDTO extends ApiResourceDTO<List<GetUserDTO>> {

    public ListUserDTO(List<GetUserDTO> data) {
        super(data);
    }
}
