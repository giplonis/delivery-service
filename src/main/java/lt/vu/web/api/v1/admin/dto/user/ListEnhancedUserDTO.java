package lt.vu.web.api.v1.admin.dto.user;

import lt.vu.web.api.v1.dto.ApiResourceDTO;

import java.util.List;

public class ListEnhancedUserDTO extends ApiResourceDTO<List<GetEnhancedUserDTO>> {

    public ListEnhancedUserDTO(List<GetEnhancedUserDTO> data) {
        super(data);
    }
}
