package bala.test.com.busycoders_launchingactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "msg";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        initView();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.tv);
        if (getIntent().hasExtra(EXTRA_MESSAGE)) {
            mTextView.setText(getIntent().getStringExtra(EXTRA_MESSAGE));
        }
    }
}
