package learn.rxandroid;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import model.Menu;
import model.MoviesResponse;
import model.ResponseApi;
import model.User;
import model.UserDetails;
import model.UserResponse;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by chris on 6/1/16.
 */
public class GitHubClient {

    private static final String GITHUB_BASE_URL = "http://api.themoviedb.org/3/";
    // URL -> http://api.themoviedb.org/3/movie/top_rated?api_key=8e37a94f07edeac0c94acd28cf768d64
    public static final String BASE_URL_ONE = "http://api.themoviedb.org/3/";
    public static final String BASE_URL_TWO = "https://git.eclipse.org/r/";
    public static final String NORMAL_GET_REQUEST_URL = "http://demo5795376.mockable.io/";
    public static final String BASE_URL_FOUR = "http://www.codemobiles.com/";

    // URL -> https://fierce-cove-29863.herokuapp.com/getAnUser/1
    public static final String USER_DETAILS_URL = "https://fierce-cove-29863.herokuapp.com/";

    // URL -> https://reqres.in/api/users
    public static final String BASE_URL_FIVE = "https://reqres.in/";

    // http://www.codemobiles.com/adhoc/feed/youtubes.json
    private static final String BASE_URL_SIX = "http://www.codemobiles.com/";

    private static GitHubClient instance;
    private GithubInterface gitHubService;

    private GitHubClient() {
        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(USER_DETAILS_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        gitHubService = retrofit.create(GithubInterface.class);
    }

    public static GitHubClient getInstance() {
        if (instance == null) {
            instance = new GitHubClient();
        }
        return instance;
    }


    public Observable<MoviesResponse> getTopRated(@NonNull String userName) {
        return gitHubService.getTopRatedMovies(userName);
    }

    public Observable<Menu> getSampleGetRequest() {
        return gitHubService.getSampleGetRequest();
    }

    public Observable<UserDetails> getUserDetails(int id) {
        return gitHubService.getUserDetails(id);
    }


    public Observable<List<ResponseApi>> postResponseWithBody() {
        return gitHubService.postResponseWithBody();
    }

    public Observable<UserResponse> postWithRequestBody(User moviePostData) {
        return gitHubService.postWithRequestBody(moviePostData);
    }
}
