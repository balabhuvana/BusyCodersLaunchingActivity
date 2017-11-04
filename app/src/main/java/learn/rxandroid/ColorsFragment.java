package learn.rxandroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bala.test.com.busycoders_launchingactivity.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ColorsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ColorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ColorsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView colorListView;
    SimpleStringAdapter simpleStringAdapter;


    public ColorsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ColorsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ColorsFragment newInstance(String param1, String param2) {
        ColorsFragment fragment = new ColorsFragment();
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
        return inflater.inflate(R.layout.fragment_colors, container, false);
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        configureLayout(view);
        createObservable();
    }

    private void createObservable() {
        /*
        Observable<List<String>> listObservable = Observable.just(getColorList());
        disposable = listObservable.subscribe(colors -> simpleStringAdapter.setStrings(colors));*/

        Observable<List<String>> listObservable = Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                emitter.onNext(getColorList());
            }
        });

        listObservable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());

        listObservable.subscribe(list -> {
            simpleStringAdapter.setStrings(list);
            simpleStringAdapter.notifyDataSetChanged();
        });
    }

    private void configureLayout(View view) {
        colorListView = (RecyclerView) view.findViewById(R.id.color_list);
        colorListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        simpleStringAdapter = new SimpleStringAdapter(getActivity());
        colorListView.setAdapter(simpleStringAdapter);
    }

    private static List<String> getColorList() {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("red");
        colors.add("green");
        colors.add("blue");
        colors.add("pink");
        colors.add("brown");
        colors.add("red");
        colors.add("green");
        colors.add("blue");
        colors.add("pink");
        colors.add("brown");
        colors.add("red");
        colors.add("green");
        colors.add("blue");
        colors.add("pink");
        colors.add("brown");
        colors.add("red");
        colors.add("green");
        colors.add("blue");
        colors.add("pink");
        colors.add("brown");
        colors.add("red");
        colors.add("green");
        colors.add("blue");
        colors.add("pink");
        colors.add("brown");
        colors.add("red");
        colors.add("green");
        colors.add("blue");
        colors.add("pink");
        colors.add("brown");
        colors.add("red");
        colors.add("green");
        colors.add("blue");
        colors.add("pink");
        colors.add("brown");
        colors.add("red");
        colors.add("green");
        colors.add("blue");
        colors.add("pink");
        colors.add("brown");
        return colors;
    }

    @Override
    public void onStop() {

        super.onStop();
    }
}
