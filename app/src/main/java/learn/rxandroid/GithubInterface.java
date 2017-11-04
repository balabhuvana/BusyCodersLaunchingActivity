package learn.rxandroid;

import java.util.List;

import model.Menu;
import model.MoviesResponse;
import model.ResponseApi;
import model.User;
import model.UserDetails;
import model.UserResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by bala on 16/10/17.
 */

public interface GithubInterface {
    @GET("movie/top_rated")
    Observable<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("getAnUser/{id}")
    Observable<UserDetails> getUserDetails(@Path("id") int id);

    @GET("testokhttp")
    Observable<Menu> getSampleGetRequest();

    @POST("api/users")
    Observable<UserResponse> postWithRequestBody(@Body User moviePostData);

    @GET("adhoc/feed/youtubes.json")
    Observable<List<ResponseApi>> postResponseWithBody();

}
