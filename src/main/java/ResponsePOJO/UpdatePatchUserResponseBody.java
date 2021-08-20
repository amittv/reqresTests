package ResponsePOJO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
public class UpdatePatchUserResponseBody {
    private String name;
    private String job;
    private Date updatedAt;

    @Setter
    private int statusCode;

}
