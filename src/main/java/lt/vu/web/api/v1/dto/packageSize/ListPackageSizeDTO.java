package lt.vu.web.api.v1.dto.packageSize;

import lt.vu.web.api.v1.dto.ApiResourceDTO;

import java.util.List;

public class ListPackageSizeDTO extends ApiResourceDTO<List<PackageSizeDTO>> {

    public ListPackageSizeDTO(List<PackageSizeDTO> data) {
        super(data);
    }
}
