package lt.vu.web.api.v1.dto.attribute;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.Attribute;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class GetAttributeDTO {

    private int id;

    private String label;

    private int additionalPrice;

    public static GetAttributeDTO createFromEntity(Attribute attribute) {
        GetAttributeDTO dto = new GetAttributeDTO();

        dto.setId(attribute.getId());
        dto.setLabel(attribute.getLabel());
        dto.setAdditionalPrice(attribute.getAdditionalPrice());

        return dto;
    }

    public static List<GetAttributeDTO> createMany(List<Attribute> attributes) {
        return attributes
                .stream()
                .map(GetAttributeDTO::createFromEntity)
                .collect(Collectors.toList());
    }
}
