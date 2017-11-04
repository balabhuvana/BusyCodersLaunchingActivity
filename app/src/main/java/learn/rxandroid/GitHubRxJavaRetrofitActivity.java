package learn.rxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import bala.test.com.busycoders_launchingactivity.MainActivity;
import bala.test.com.busycoders_launchingactivity.R;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rx.Subscription;

public class GitHubRxJavaRetrofitActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private GitHubRepoAdapter adapter = new GitHubRepoAdapter();
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_hub_rx_java_retrofit);


        final ListView listView = (ListView) findViewById(R.id.list_view_repos);
        listView.setAdapter(adapter);

        final EditText editTextUsername = (EditText) findViewById(R.id.edit_text_username);
        final Button buttonSearch = (Button) findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = editTextUsername.getText().toString();
                if (!TextUtils.isEmpty(username)) {
                    getStarredRepos(username);
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    private void getStarredRepos(String username) {
        GitHubClientRxAndroid mGitHubClientRxAndroid = GitHubClientRxAndroid.getInstance();
        Observable<List<GitHubRepoRxAndroid>> observable = mGitHubClientRxAndroid.getStarredRepos(username);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<GitHubRepoRxAndroid>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "In onSubscribe()");
                    }

                    @Override
                    public void onNext(List<GitHubRepoRxAndroid> gitHubRepoRxAndroids) {
                        Log.d(TAG, "In onNext()");
                        adapter.setGitHubRepos(gitHubRepoRxAndroids);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "In onComplete()");
                    }
                });

    }
}
