package lt.vu.web.api.dto.packageSize;

import lt.vu.web.api.dto.ApiResourceDTO;

import java.util.List;

public class ListPackageSizeDTO extends ApiResourceDTO<List<PackageSizeDTO>> {

    public ListPackageSizeDTO(List<PackageSizeDTO> data) {
        super(data);
    }
}
