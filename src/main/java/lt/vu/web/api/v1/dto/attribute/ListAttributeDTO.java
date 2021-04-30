package lt.vu.web.api.v1.dto.attribute;

import lt.vu.web.api.v1.dto.ApiResourceDTO;

import java.util.List;

public class ListAttributeDTO extends ApiResourceDTO<List<GetAttributeDTO>> {

    public ListAttributeDTO(List<GetAttributeDTO> data) {
        super(data);
    }
}
