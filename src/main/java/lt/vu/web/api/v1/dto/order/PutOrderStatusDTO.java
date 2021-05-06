package lt.vu.web.api.v1.dto.order;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;

@Getter @Setter
public class PutOrderStatusDTO {

    @NotNull
    @FormParam("id")
    private int id;
}
