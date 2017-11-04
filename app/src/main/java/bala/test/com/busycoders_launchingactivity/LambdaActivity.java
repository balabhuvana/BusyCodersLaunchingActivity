package bala.test.com.busycoders_launchingactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import lambda.LambdaFragment;
import lambda.StateChangeListener;

public class LambdaActivity extends AppCompatActivity implements LambdaFragment.OnHeadlineSelectedListener , StateChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_lambda);




        getSupportFragmentManager().beginTransaction().replace(R.id.lambdaFrameContent, LambdaFragment.newInstance("", "")).commit();
    }

    @Override
    public void onArticleSelected(int position) {
        Toast.makeText(this, "Hello Lambda", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStateChange(int oldState, int newState) {
        Toast.makeText(this, "Hello LambdaActivity - onStateChange", Toast.LENGTH_SHORT).show();
    }


}
