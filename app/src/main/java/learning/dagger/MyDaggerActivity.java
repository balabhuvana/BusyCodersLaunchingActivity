package learning.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import bala.test.com.busycoders_launchingactivity.R;

public class MyDaggerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dagger);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.daggerFrameLayout, MyDaggerFragment.newInstance("", ""))
                .commit();
    }
}
