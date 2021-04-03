package lt.vu.web.api.dto.postDtos.order;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderDTO {

    private SenderDTO sender;

    private SenderDTO recipient;

    private int packageOptionId;

    private String pickUpDate;

}
