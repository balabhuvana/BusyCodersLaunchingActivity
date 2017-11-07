package learning.dagger;

import java.util.List;

import model.Menu;
import model.MoviesResponse;
import model.ResponseApi;
import model.User;
import model.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by bala on 16/10/17.
 */

public interface DaggerRetrofitInterface {

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("testokhttp")
    Call<Menu> getSampleGetRequest();

    @POST("api/users")
    Call<UserResponse> postWithRequestBody(@Body User moviePostData);

    @GET("adhoc/feed/youtubes.json")
    Call<List<ResponseApi>> postResponseWithBody();

}
