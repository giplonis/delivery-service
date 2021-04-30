package lt.vu.web.api.v1.dto.order;

import lombok.Getter;
import lombok.Setter;
import lt.vu.web.api.v1.dto.userInfo.PostUserInfoDTO;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import java.util.Date;
import java.util.List;

@Getter @Setter
public class PostOrderDTO {

    @NotNull
    @FormParam("sender")
    @Valid
    private PostUserInfoDTO sender;

    @NotNull
    @FormParam("recipient")
    @Valid
    private PostUserInfoDTO recipient;

    @NotNull
    @FormParam("packageOptionId")
    private int packageOptionId;

    @NotNull
    @Future
    @FormParam("pickUpDate")
    private Date pickUpDate;

    @NotNull
    @FormParam("attributes")
    private List<Integer> attributes;
}
