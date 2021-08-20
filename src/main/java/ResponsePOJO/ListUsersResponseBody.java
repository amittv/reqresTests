package ResponsePOJO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class ListUsersResponseBody {

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
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;

        public Datum(){

        }
    }

    @Getter
    public static class Support{
        private String url;
        private String text;
        public Support(){

        }
    }
}
