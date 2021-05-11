package lt.vu.web.api.v1.admin.dto.order;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.orm.entities.OrderStatus;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;

@Getter @Setter
public class PutOrderStatusDTO {

    @NotNull
    @FormParam("orderStatus")
    private String orderStatus;
}
