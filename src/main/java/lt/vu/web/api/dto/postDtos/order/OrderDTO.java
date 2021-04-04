package lt.vu.web.api.dto.postDtos.order;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class OrderDTO {

    private SenderDTO sender;

    private SenderDTO recipient;

    private int packageOptionId;

    private Date pickUpDate;

}
