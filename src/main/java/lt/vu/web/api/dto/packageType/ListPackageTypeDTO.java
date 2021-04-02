package lt.vu.web.api.dto.packageType;

import lt.vu.web.api.dto.ApiResourceDTO;

import java.util.List;

public class ListPackageTypeDTO extends ApiResourceDTO<List<PackageTypeDTO>> {

    public ListPackageTypeDTO(List<PackageTypeDTO> data) {
        super(data);
    }
}
