package lt.vu.web.api.v1.dto.getDto.packageSize;

import lt.vu.web.api.v1.dto.getDto.ApiResourceDTO;

import java.util.List;

public class ListPackageSizeDTO extends ApiResourceDTO<List<PackageSizeDTO>> {

    public ListPackageSizeDTO(List<PackageSizeDTO> data) {
        super(data);
    }
}
