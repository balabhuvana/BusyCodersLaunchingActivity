package bala.test.com.busycoders_launchingactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class LifeCycleLoggingSecondActivity extends AppCompatActivity {

    private String TAG = LifeCycleLoggingSecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_logging_second);

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

}
