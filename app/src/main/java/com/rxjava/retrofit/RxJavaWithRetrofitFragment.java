package com.rxjava.retrofit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import bala.test.com.busycoders_launchingactivity.R;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import model.MoviesResponse;
import retrofit.ApiClient;
import retrofit.GithubInterface;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RxJavaWithRetrofitFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RxJavaWithRetrofitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RxJavaWithRetrofitFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String TAG = RxJavaWithRetrofitFragment.class.getSimpleName();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private final static String API_KEY = "8e37a94f07edeac0c94acd28cf768d64";
    private RxJavaRetrofitInterface mRxJavaRetrofitInterface = null;
    private RecyclerView mRecyclerView = null;


    public RxJavaWithRetrofitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RxJavaWithRetrofitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RxJavaWithRetrofitFragment newInstance(String param1, String param2) {
        RxJavaWithRetrofitFragment fragment = new RxJavaWithRetrofitFragment();
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
        return inflater.inflate(R.layout.fragment_rx_java_with_retrofit, container, false);
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

        Button button = (Button) view.findViewById(R.id.btnRxJavaRetrofit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRequestWithQueryParmsOne();
            }
        });
    }

    public void getRequestWithQueryParmsOne() {

        if (API_KEY.isEmpty()) {
            Toast.makeText(getContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        mRxJavaRetrofitInterface = RxJavaRetrofitApiClient.getClient().create(RxJavaRetrofitInterface.class);
        Observable<MoviesResponse> observable = mRxJavaRetrofitInterface.getTopRatedMovies(API_KEY);
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<MoviesResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(MoviesResponse moviesResponse) {
                Log.d(TAG, "onNext");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });

    }
}
