package adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import bala.test.com.busycoders_launchingactivity.R;
import fragments.EditorFragments;

/**
 * Created by bala on 15/10/17.
 */

public class SamplePagerAdapter extends FragmentPagerAdapter {
    private Context mContext = null;

    public SamplePagerAdapter(Context oContext, FragmentManager fm) {
        super(fm);
        mContext = oContext;
    }

    @Override
    public Fragment getItem(int position) {
        return EditorFragments.newInstance(position, "");
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return EditorFragments.getTitle(mContext, position);
    }


}
