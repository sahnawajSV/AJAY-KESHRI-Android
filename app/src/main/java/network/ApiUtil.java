package network;

import retrofit2.Retrofit;

/**
 * Created by ajay on 8/9/17.
 */

public class ApiUtil {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static MovieServer getMovieServer() {
        return RetrofitApiCall.getClient(BASE_URL).create(MovieServer.class);
    }
}
