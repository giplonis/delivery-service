package lt.vu.web.api.v1.dto.get.packageOption;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.PackageOption;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class PackageOptionDTO {

    private int id;

    private int price;

    private int packageSize;

    private int packageType;

    private boolean fragile;

    public static PackageOptionDTO createFromEntity(PackageOption packageOption) {
        PackageOptionDTO dto = new PackageOptionDTO();

        dto.setId(packageOption.getId());
        dto.setPrice(packageOption.getPrice());
        dto.setPackageSize(packageOption.getPackageSize().getId());
        dto.setPackageType(packageOption.getPackageType().getId());
        dto.setFragile(packageOption.isFragile());

        return dto;
    }

    public static List<PackageOptionDTO> createMany(List<PackageOption> packageTypes) {
        return packageTypes
                .stream()
                .map(PackageOptionDTO::createFromEntity)
                .collect(Collectors.toList());
    }
}
