package bala.test.com.busycoders_launchingactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fragments.SimpleContentFragments;

public class SimpleContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_content);

        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            getFragmentManager().beginTransaction().add(android.R.id.content, SimpleContentFragments.newInstance("https://google.com", "")).commit();
        }
    }

}
