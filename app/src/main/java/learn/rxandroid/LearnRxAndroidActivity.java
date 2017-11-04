package learn.rxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import bala.test.com.busycoders_launchingactivity.R;

public class LearnRxAndroidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_rx_android);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutRxAndroid, RXAndroidWithRetrofitFragment.newInstance("", "")).commit();
    }
}
