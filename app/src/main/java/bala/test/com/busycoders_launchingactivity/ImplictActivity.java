package bala.test.com.busycoders_launchingactivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ImplictActivity extends AppCompatActivity {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implict);

        initView();
    }

    private void initView() {
        mEditText = (EditText) findViewById(R.id.et);
    }

    public void showMeImplicitIntent(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mEditText.getText().toString())));
    }
}
