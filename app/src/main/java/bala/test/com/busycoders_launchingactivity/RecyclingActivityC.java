package bala.test.com.busycoders_launchingactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class RecyclingActivityC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycling_c);

    }

    public void showRecyclingActivityA(View view) {
        Intent mIntent = new Intent(this, RecyclingActivityA.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(mIntent);
    }

    public void showRecyclingActivitySingleTop(View view) {
        Intent mIntent = new Intent(this, RecyclingActivityA.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(mIntent);
    }

}
