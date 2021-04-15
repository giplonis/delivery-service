package lt.vu.web.api.v1.dto.packageSize;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.PackageSize;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class GetPackageSizeDTO {

    private int id;

    private String title;

    private int maxWeight;

    private int length;

    private int height;

    private int width;

    public static GetPackageSizeDTO createFromEntity(PackageSize packageSize) {
        GetPackageSizeDTO dto = new GetPackageSizeDTO();

        dto.setId(packageSize.getId());
        dto.setTitle(packageSize.getTitle());
        dto.setMaxWeight(packageSize.getMaxWeight());
        dto.setLength(packageSize.getLength());
        dto.setHeight(packageSize.getHeight());
        dto.setWidth(packageSize.getWidth());

        return dto;
    }

    public static List<GetPackageSizeDTO> createMany(List<PackageSize> packageSizes) {
        return packageSizes
                .stream()
                .map(GetPackageSizeDTO::createFromEntity)
                .collect(Collectors.toList());
    }
}
