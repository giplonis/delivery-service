package lt.vu.web.api.v1.dto.postDto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
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
