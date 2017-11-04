package bala.test.com.busycoders_launchingactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import retrofit.RetrofitFragment;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_retrofit);

        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, RetrofitFragment.newInstance("", "")).commit();
        }
    }

}
