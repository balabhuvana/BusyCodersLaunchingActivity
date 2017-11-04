package com.rxjava.retrofit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import bala.test.com.busycoders_launchingactivity.R;
import learn.rxandroid.GitHubClient;
import model.Menu;
import model.Movie;
import model.MoviePostData;
import model.MoviesResponse;
import model.ResponseApi;
import model.User;
import model.UserDetails;
import model.UserResponse;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RXjavaWithRetrofitFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RXjavaWithRetrofitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RXjavaWithRetrofitFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String TAG = RXjavaWithRetrofitFragment.class.getSimpleName();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private final static String API_KEY = "8e37a94f07edeac0c94acd28cf768d64";


    public RXjavaWithRetrofitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RXjavaWithRetrofitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RXjavaWithRetrofitFragment newInstance(String param1, String param2) {
        RXjavaWithRetrofitFragment fragment = new RXjavaWithRetrofitFragment();
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
        return inflater.inflate(R.layout.fragment_rxjava_with_retrofit, container, false);
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

    public String createJsonObjectForPost() {
        MoviePostData moviePostData = new MoviePostData();
        moviePostData.setName("Ananth");
        moviePostData.setJob("Cashier");
        return new Gson().toJson(moviePostData);
    }

    public User createUserObjectForPost() {
        User user = new User();
        user.setJob("Cashier");
        user.setName("Ananth");
        return user;
    }

    public void getRequestWithQueryRXJavaWithRetrofit() {

        if (API_KEY.isEmpty()) {
            Toast.makeText(getContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        GitHubClient.getInstance()
                .getTopRated(API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<MoviesResponse>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(MoviesResponse moviesResponse) {
                for (Movie movie : moviesResponse.getResults()) {
                    Log.d(TAG, "onNext - " + movie.getOriginalTitle());
                }
            }
        });

    }

    private void normalGetRequestWithRXJavaWithRetrofit() {

        // URL -> http://demo5795376.mockable.io/testokhttp

        GitHubClient.getInstance()
                .getSampleGetRequest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Menu>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Menu menu) {
                        Log.i(TAG, "" + menu.getId());
                    }
                });
    }

    private void postRequestWithResponseBody() {

        GitHubClient.getInstance()
                .postResponseWithBody()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<ResponseApi>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<ResponseApi> responseApis) {
                        Log.i(TAG, "" + responseApis.size());
                    }
                });
    }

    private void postWithRequestBody() {

        GitHubClient.getInstance()
                .postWithRequestBody(createUserObjectForPost())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<UserResponse>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UserResponse userResponse) {
                Log.i(TAG, "" + userResponse.getCreatedAt());
            }
        });

    }

    private void postRequestWithPath() {

        GitHubClient.getInstance()
                .getUserDetails(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserDetails>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(UserDetails userDetails) {
                        Log.d(TAG, "" + userDetails.firstname);
                    }
                });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = (Button) view.findViewById(R.id.bntRxJavaWithRetrofit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postRequestWithPath();
            }
        });
    }
}
