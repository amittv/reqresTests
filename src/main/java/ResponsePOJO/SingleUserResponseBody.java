package ResponsePOJO;

import lombok.Getter;
import lombok.Setter;

@Getter
public class SingleUserResponseBody {

    private Data data;
    private Support support;

    @Setter
    private int statusCode;

    @Getter
    public static class Data{
        private int id;
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;
    }

    @Getter
    public static class Support{
        private String url;
        private String text;
    }

    public SingleUserResponseBody(){

    }
}
