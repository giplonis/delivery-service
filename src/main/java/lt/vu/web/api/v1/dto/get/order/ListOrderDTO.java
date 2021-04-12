package lt.vu.web.api.v1.dto.get.order;

import lt.vu.web.api.v1.dto.get.ApiResourceDTO;

import java.util.List;

public class ListOrderDTO extends ApiResourceDTO<List<OrderDTO>> {

    public ListOrderDTO(List<OrderDTO> data) {
        super(data);
    }
}
