package lt.vu.web.api.v1.dto.order;

import lombok.Getter;
import lombok.Setter;
import lt.vu.web.api.v1.dto.userInfo.PostUserInfoDTO;

import javax.enterprise.context.RequestScoped;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import java.util.Date;
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
}
