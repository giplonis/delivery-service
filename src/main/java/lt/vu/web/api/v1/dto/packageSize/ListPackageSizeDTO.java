package lt.vu.web.api.v1.dto.packageSize;

import lt.vu.web.api.v1.dto.ApiResourceDTO;

import java.util.List;

public class ListPackageSizeDTO extends ApiResourceDTO<List<GetPackageSizeDTO>> {

    public ListPackageSizeDTO(List<GetPackageSizeDTO> data) {
        super(data);
    }
}
