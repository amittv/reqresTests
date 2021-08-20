package ResponsePOJO;

import lombok.Getter;
import lombok.Setter;

@Getter
public class SingleResourceResponseBody {

    private Data data;
    private Support support;
    @Setter
    private int statusCode;

    @Getter
    public static class Data{
        private int id;
        private String name;
        private int year;
        private String color;
        private String pantone_value;
    }

    @Getter
    public static class Support{
        private String url;
        private String text;
    }

    private SingleResourceResponseBody(){

    }
}
