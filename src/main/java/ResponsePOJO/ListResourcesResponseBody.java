package ResponsePOJO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class ListResourcesResponseBody {

    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<Datum> data;
    private Support support;

    @Setter
    private int statusCode;

    @Getter
    public static class Datum{
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

    public ListResourcesResponseBody(){

    }
}
