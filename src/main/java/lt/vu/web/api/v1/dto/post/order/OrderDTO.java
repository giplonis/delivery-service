package lt.vu.web.api.v1.dto.post.order;

import lombok.Getter;
import lombok.Setter;
import lt.vu.web.api.v1.dto.post.userInfo.UserInfoDTO;

import java.util.Date;

@Getter @Setter
public class OrderDTO {

    private UserInfoDTO sender;

    private UserInfoDTO recipient;

    private int packageOptionId;

    private Date pickUpDate;
}
