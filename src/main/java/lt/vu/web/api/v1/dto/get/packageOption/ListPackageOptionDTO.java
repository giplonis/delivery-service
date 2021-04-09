package lt.vu.web.api.v1.dto.get.packageOption;

import lt.vu.web.api.v1.dto.get.ApiResourceDTO;

import java.util.List;

public class ListPackageOptionDTO extends ApiResourceDTO<List<PackageOptionDTO>> {

    public ListPackageOptionDTO(List<PackageOptionDTO> data) {
        super(data);
    }
}
