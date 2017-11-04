package bala.test.com.busycoders_launchingactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fragments.MyServiceFragment;

public class MyServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);

        getSupportFragmentManager().beginTransaction().replace(R.id.myServiceFrameLayout, MyServiceFragment.newInstance("", "")).commit();

    }
}
