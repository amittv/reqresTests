package ResponsePOJO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class UpdateUserResponseBody {

    public String name;
    public String job;
    public Date updatedAt;

    @Setter
    public int statusCode;

    public UpdateUserResponseBody(){

    }
}
