package bala.test.com.busycoders_launchingactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ExtrasDemoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras_demo);

    }

    public void showOtherActivityWithExtras(View view) {
        Intent mIntent = new Intent(this, OtherActivity.class);
        mIntent.putExtra(OtherActivity.EXTRA_MESSAGE, getString(R.string.other_activity));
        startActivity(mIntent);
    }

}
