package bala.test.com.busycoders_launchingactivity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import adapter.SamplePagerAdapter;

public class ViewPagerFragmentDemoActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_fragment_demo);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new SamplePagerAdapter(getApplicationContext(), getSupportFragmentManager()));
    }

}
