package ResponsePOJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@JsonIgnoreProperties
public class RegisterResponseBody {
    private int id;
    private String token;
    private String error;

    @Setter
    private int statusCode;
}
