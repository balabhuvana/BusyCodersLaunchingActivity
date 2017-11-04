package retrofit;

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

import com.google.gson.Gson;

import java.util.List;

import bala.test.com.busycoders_launchingactivity.R;
import model.Movie;
import model.MoviesResponse;
import okhttp.Menu;
import okhttp.MoviePostData;
import okhttp.ResponseApi;
import okhttp.User;
import okhttp.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RetrofitFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RetrofitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RetrofitFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private final static String API_KEY = "8e37a94f07edeac0c94acd28cf768d64";
    private GithubInterface mGithubInterface = null;
    private RecyclerView mRecyclerView = null;
    private String TAG = RetrofitFragment.class.getSimpleName();

    public RetrofitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RetrofitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RetrofitFragment newInstance(String param1, String param2) {
        RetrofitFragment fragment = new RetrofitFragment();
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
        return inflater.inflate(R.layout.fragment_retrofit, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = (Button) view.findViewById(R.id.btnRetrofit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRequestWithQueryParmsOne();
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

    public void getRequestWithQueryParmsOne() {

        if (API_KEY.isEmpty()) {
            Toast.makeText(getContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        mGithubInterface = ApiClient.getClient().create(GithubInterface.class);
        Call<MoviesResponse> call = mGithubInterface.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
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

        //mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        // mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void normalGetRequest() {
        Retrofit retrofit = ApiClient.getClient();
        GithubInterface githubInterface = retrofit.create(GithubInterface.class);
        Call<Menu> call = githubInterface.getSampleGetRequest();
        call.enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {

                Log.i(TAG, "URL : " + call.request().url());
                if (response.isSuccessful()) {
                    Menu menu = response.body();
                    Log.i(TAG, "" + menu.getId());


                } else {
                    Log.i(TAG, "Response Unsuccessfull");
                }
            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {
                Log.i(TAG, "Response onFailure");
            }
        });
    }

    private void postRequestWithResponseBody() {
        Retrofit retrofit = ApiClient.getClient();
        GithubInterface githubInterface = retrofit.create(GithubInterface.class);
        Call<List<ResponseApi>> call = githubInterface.postResponseWithBody();

        call.enqueue(new Callback<List<ResponseApi>>() {
            @Override
            public void onResponse(Call<List<ResponseApi>> call, Response<List<ResponseApi>> response) {
                Log.d(TAG, "URL" + call.request().url());
                if (response.isSuccessful()) {
                    List<ResponseApi> responseApiList = response.body();
                    Log.i(TAG, "" + responseApiList.size());

                } else {
                    Log.i(TAG, "Response Unsuccessfull");
                }
            }

            @Override
            public void onFailure(Call<List<ResponseApi>> call, Throwable t) {

                Log.i(TAG, "onFailure");
            }
        });
    }

    private void postWithRequestBody() {
        Retrofit retrofit = ApiClient.getClient();
        GithubInterface githubInterface = retrofit.create(GithubInterface.class);
        Call<UserResponse> call = githubInterface.postWithRequestBody(createUserObjectForPost());

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Log.d(TAG, "URL" + call.request().url());
                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body();
                    Log.i(TAG, "" + userResponse.getCreatedAt());

                } else {
                    Log.i(TAG, "Response Unsuccessfull");
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.i(TAG, "onFailure");
            }
        });

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
}
