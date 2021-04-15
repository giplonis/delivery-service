package lt.vu.web.api.v1.dto.packageType;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.PackageType;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class GetPackageTypeDTO {

    private int id;

    private String title;

    private String description;

    public static GetPackageTypeDTO createFromEntity(PackageType packageType) {
        GetPackageTypeDTO dto = new GetPackageTypeDTO();

        dto.setId(packageType.getId());
        dto.setTitle(packageType.getTitle());
        dto.setDescription(packageType.getDescription());

        return dto;
    }

    public static List<GetPackageTypeDTO> createMany(List<PackageType> packageTypes) {
        return packageTypes
                .stream()
                .map(GetPackageTypeDTO::createFromEntity)
                .collect(Collectors.toList());
    }
}
