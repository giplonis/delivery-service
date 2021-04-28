package lt.vu.web.api.v1.dto.packageOption;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.PackageOption;
import lt.vu.web.api.v1.dto.packageSize.GetPackageSizeDTO;
import lt.vu.web.api.v1.dto.packageType.GetPackageTypeDTO;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class GetPackageOptionDTO {

    private int id;

    private int price;

    private GetPackageSizeDTO packageSize;

    private GetPackageTypeDTO packageType;

    public static GetPackageOptionDTO createFromEntity(PackageOption packageOption) {
        GetPackageOptionDTO dto = new GetPackageOptionDTO();

        dto.setId(packageOption.getId());
        dto.setPrice(packageOption.getPrice());
        dto.setPackageSize(GetPackageSizeDTO.createFromEntity(packageOption.getPackageSize()));
        dto.setPackageType(GetPackageTypeDTO.createFromEntity(packageOption.getPackageType()));

        return dto;
    }

    public static List<GetPackageOptionDTO> createMany(List<PackageOption> packageTypes) {
        return packageTypes
                .stream()
                .map(GetPackageOptionDTO::createFromEntity)
                .collect(Collectors.toList());
    }
}
