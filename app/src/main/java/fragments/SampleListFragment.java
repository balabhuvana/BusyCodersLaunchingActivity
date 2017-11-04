package fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import bala.test.com.busycoders_launchingactivity.R;


public class SampleListFragment extends ListFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters

    private static final String[] items = {"Apple", "Banana", "Chitra", "Deva", "Karthik", "Bala", "Arun", "nelson"};


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SampleListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SampleListFragment newInstance(int columnCount) {
        SampleListFragment fragment = new SampleListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                return (true);
            case R.id.help:
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }

}
