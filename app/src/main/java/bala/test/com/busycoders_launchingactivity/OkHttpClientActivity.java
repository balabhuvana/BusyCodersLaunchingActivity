package bala.test.com.busycoders_launchingactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import okhttp.OkHttpClientFragment;

public class OkHttpClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_ok_http_client);
        getSupportFragmentManager().beginTransaction().replace(R.id.okhttpContainer, OkHttpClientFragment.newInstance("", "")).commit();

    }

}
