package lt.vu.web.api.v1.dto.packageType;

import lt.vu.web.api.v1.dto.ApiResourceDTO;

import java.util.List;

public class ListPackageTypeDTO extends ApiResourceDTO<List<GetPackageTypeDTO>> {

    public ListPackageTypeDTO(List<GetPackageTypeDTO> data) {
        super(data);
    }
}
