package ResponsePOJO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class CreateUserResponseBody {
        private String name;
        private String job;
        private String id;
        private Date createdAt;

        @Setter
        private int statusCode;

        private CreateUserResponseBody(){

        }
}
