package lt.vu.web.api.dto.postDtos.order;

import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbAnnotation;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeDeserializer;
import java.util.Objects;

@Getter @Setter
public class AddressDTO {

    private String city;

    private String street;
}
