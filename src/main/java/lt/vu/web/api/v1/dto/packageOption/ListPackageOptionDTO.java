package lt.vu.web.api.v1.dto.packageOption;

import lt.vu.web.api.v1.dto.ApiResourceDTO;

import java.util.List;

public class ListPackageOptionDTO extends ApiResourceDTO<List<GetPackageOptionDTO>> {

    public ListPackageOptionDTO(List<GetPackageOptionDTO> data) {
        super(data);
    }
}
