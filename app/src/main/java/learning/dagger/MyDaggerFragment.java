package learning.dagger;

import android.content.Context;
import android.content.SharedPreferences;
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

import java.util.List;

import javax.inject.Inject;

import bala.test.com.busycoders_launchingactivity.R;
import model.Movie;
import model.MoviesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MyDaggerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String TAG = MyDaggerFragment.class.getSimpleName();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private final static String API_KEY = "8e37a94f07edeac0c94acd28cf768d64";

    @Inject
    SharedPreferences mPreferences;

    @Inject
    Retrofit mRetrofit;


    public MyDaggerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyDaggerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyDaggerFragment newInstance(String param1, String param2) {
        MyDaggerFragment fragment = new MyDaggerFragment();
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

        ((MyApplication) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_dagger, container, false);
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnDagger = (Button) view.findViewById(R.id.btnDagger);
        btnDagger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPreferences != null) {
                    Toast.makeText(getActivity(), "mPreferences injected Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "mPreferences object injected Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnRetrofit = (Button) view.findViewById(R.id.btnRetrofit);
        btnRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRetrofit != null) {
                    Toast.makeText(getActivity(), "Retrofit object injected Successfully", Toast.LENGTH_SHORT).show();
                    DaggerRetrofitInterface mDaggerRetrofitInterface = mRetrofit.create(DaggerRetrofitInterface.class);
                    Call<MoviesResponse> responseCall = mDaggerRetrofitInterface.getTopRatedMovies(API_KEY);
                    responseCall.enqueue(new Callback<MoviesResponse>() {
                        @Override
                        public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                            Log.d(TAG, "URL: " + call.request().url());
                            List<Movie> list = response.body().getResults();
//                mRecyclerView.setAdapter(new MoviesAdapter(list, R.layout.list_item_movie, getContext()));
                            Log.d(TAG, "Number of movies received: " + list.size());
                        }

                        @Override
                        public void onFailure(Call<MoviesResponse> call, Throwable t) {
                            Log.e(TAG, t.toString());
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "Retrofit object is not injected Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
