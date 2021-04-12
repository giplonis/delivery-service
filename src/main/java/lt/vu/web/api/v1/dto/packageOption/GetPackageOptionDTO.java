package lt.vu.web.api.v1.dto.packageOption;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.PackageOption;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class GetPackageOptionDTO {

    private int id;

    private int price;

    private int packageSize;

    private int packageType;

    public static GetPackageOptionDTO createFromEntity(PackageOption packageOption) {
        GetPackageOptionDTO dto = new GetPackageOptionDTO();

        dto.setId(packageOption.getId());
        dto.setPrice(packageOption.getPrice());
        dto.setPackageSize(packageOption.getPackageSize().getId());
        dto.setPackageType(packageOption.getPackageType().getId());

        return dto;
    }

    public static List<GetPackageOptionDTO> createMany(List<PackageOption> packageTypes) {
        return packageTypes
                .stream()
                .map(GetPackageOptionDTO::createFromEntity)
                .collect(Collectors.toList());
    }
}
