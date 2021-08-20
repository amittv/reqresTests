package RequestPOJO;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateUpdateUserRequestBody {

    private String name;
    private String job;

}
