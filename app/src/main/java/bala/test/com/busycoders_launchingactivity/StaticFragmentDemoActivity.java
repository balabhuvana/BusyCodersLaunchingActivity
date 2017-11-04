package bala.test.com.busycoders_launchingactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class StaticFragmentDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_static_fragment_demo);

    }

    public void showOther(View mView) {
        Intent mIntent = new Intent(this, OtherActivity.class);
        mIntent.putExtra(OtherActivity.EXTRA_MESSAGE, getString(R.string.static_activity));
        startActivity(mIntent);
    }
}
