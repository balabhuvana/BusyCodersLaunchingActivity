package fragments;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import bala.test.com.busycoders_launchingactivity.R;
import service.MyIntentService;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyServiceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyServiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyServiceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "service.action.FOO";
    private static final String ACTION_BAZ = "service.action.BAZ";
    private static final String ACTION_DOWNLOAD = "service.network.download";
    private static final String DOWNLOADED_RESPONSE = "service.network.response";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "service.extra.PARAM2";
    private static final String EXTRA_URL = "service.extra.URL";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MyServiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyServiceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyServiceFragment newInstance(String param1, String param2) {
        MyServiceFragment fragment = new MyServiceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnService = (Button) view.findViewById(R.id.btnService);
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDownloadServices(getContext(), "http://demo5795376.mockable.io/testokhttp");
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public void startDownloadServices(Context context, String url) {

        IntentFilter intentFilter = new IntentFilter(MySampleBroadCast.PROCESS_RESPONSE);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        getActivity().registerReceiver(new MySampleBroadCast(), intentFilter);

        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_DOWNLOAD);
        intent.putExtra(EXTRA_URL, url);
        context.startService(intent);
    }

    public class MySampleBroadCast extends BroadcastReceiver {
        public static final String PROCESS_RESPONSE = "fragments.intent.action.PROCESS_RESPONSE";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("MySampleBroadCast", "onReceive");
            if (intent != null) {
                if (intent.hasExtra(DOWNLOADED_RESPONSE)) {
                    Log.d("MySampleBroadCast", intent.getStringExtra(DOWNLOADED_RESPONSE));
                }
            }
        }
    }
}
