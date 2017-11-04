package learn.rxandroid;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bala on 30/10/17.
 */

public interface GitHubServiceRXAndroid {
    @GET("users/{user}/starred")
    Observable<List<GitHubRepoRxAndroid>> getStarredRepositories(@Path("user") String userName);
}
