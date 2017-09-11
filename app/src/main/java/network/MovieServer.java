package network;

import model.MoviesList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ajay on 8/9/17.
 */

public interface MovieServer {
  //  https://api.themoviedb.org/3/discover/movie?api_key=626d05abf324b3be1c089c695497d49c

    @GET("discover/movie?api_key=626d05abf324b3be1c089c695497d49c")
    Call<MoviesList> getMoviesList();
}
