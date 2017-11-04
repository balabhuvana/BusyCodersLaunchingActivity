package bala.test.com.busycoders_launchingactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class RecyclingActivityA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycling_activity);

    }

    public void showRecyclingActivityB(View view) {
        startActivity(new Intent(this, RecyclingActivityB.class));
    }

}
