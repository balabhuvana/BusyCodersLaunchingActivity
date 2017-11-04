package bala.test.com.busycoders_launchingactivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fragments.SampleListFragment;

public class OtherListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager mFragmentManager = getFragmentManager();
        if (mFragmentManager.findFragmentById(android.R.id.content) == null) {
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.add(android.R.id.content,SampleListFragment.newInstance(1)).commit();
        }

    }

}
