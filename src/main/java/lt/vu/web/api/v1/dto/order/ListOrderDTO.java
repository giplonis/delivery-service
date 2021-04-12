package lt.vu.web.api.v1.dto.order;

import lt.vu.web.api.v1.dto.ApiResourceDTO;

import java.util.List;

public class ListOrderDTO extends ApiResourceDTO<List<GetOrderDTO>> {

    public ListOrderDTO(List<GetOrderDTO> data) {
        super(data);
    }
}
