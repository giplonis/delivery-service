package lt.vu.web.api.dto;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.PackageOption;
import lt.vu.persistence.orm.entities.PackageType;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class PackageOptionDTO {

    private int price;

    private int PackageSize;

    private int PackageType;

    public static PackageOptionDTO createFromEntity(PackageOption packageOption) {
        PackageOptionDTO dto = new PackageOptionDTO();

        dto.setPrice(packageOption.getPrice());
        dto.setPackageSize(packageOption.getPackageSize().getId());
        dto.setPackageType(packageOption.getPackageType().getId());

        return dto;
    }

    public static List<PackageOptionDTO> createMany(List<PackageOption> packageTypes) {
        return packageTypes
                .stream()
                .map(PackageOptionDTO::createFromEntity)
                .collect(Collectors.toList());
    }
}
