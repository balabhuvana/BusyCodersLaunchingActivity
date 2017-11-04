package learn.rxandroid;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bala on 30/10/17.
 */

public class GitHubClientRxAndroid {
    private static final String GITHUB_BASE_URL = "https://api.github.com/";

    private static GitHubClientRxAndroid instance;
    private GitHubServiceRXAndroid gitHubService;

    private GitHubClientRxAndroid() {
        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(GITHUB_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        gitHubService = retrofit.create(GitHubServiceRXAndroid.class);
    }

    public static GitHubClientRxAndroid getInstance() {
        if (instance == null) {
            instance = new GitHubClientRxAndroid();
        }
        return instance;
    }

    public Observable<List<GitHubRepoRxAndroid>> getStarredRepos(@NonNull String userName) {
        return gitHubService.getStarredRepositories(userName);
    }
}
