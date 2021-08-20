package ResponsePOJO;

import lombok.Getter;
import lombok.Setter;

@Getter
public class RegisterUserResponseBody {
    public int id;
    public String token;

    @Setter
    public int statusCode;

    public RegisterUserResponseBody(){

    }
}
