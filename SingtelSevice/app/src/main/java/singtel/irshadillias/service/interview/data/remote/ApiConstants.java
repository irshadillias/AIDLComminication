package singtel.irshadillias.service.interview.data.remote;

public class ApiConstants {
    public static final String BASE_URL = "https://postman-echo.com/";
    public static final long CONNECT_TIMEOUT = 30000;
    public static final long READ_TIMEOUT = 30000;
    public static final long WRITE_TIMEOUT = 30000;
    public static final String API_KEY = "bbde268f737aa60901e066a54b00d8cf";
    public static final String IMAGE_URL ="https://image.tmdb.org/t/p/w500/%s";

    private ApiConstants(){
        // Private constructor to hide the implicit one
    }
}
