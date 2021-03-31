package lt.vu.web.api.dto;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.PackageType;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class PackageTypeDTO {

    private int id;

    private String title;

    private String description;

    public static PackageTypeDTO createFromEntity(PackageType packageType) {
        PackageTypeDTO dto = new PackageTypeDTO();

        dto.setId(packageType.getId());
        dto.setTitle(packageType.getTitle());
        dto.setDescription(packageType.getDescription());

        return dto;
    }

    public static List<PackageTypeDTO> createMany(List<PackageType> packageTypes) {
        return packageTypes
                .stream()
                .map(PackageTypeDTO::createFromEntity)
                .collect(Collectors.toList());
    }
}
