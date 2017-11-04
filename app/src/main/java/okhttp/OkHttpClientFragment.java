package okhttp;

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import bala.test.com.busycoders_launchingactivity.R;
import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okio.BufferedSink;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OkHttpClientFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OkHttpClientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OkHttpClientFragment extends Fragment implements Runnable {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btnOkHttp;
    private final OkHttpClient client = new OkHttpClient();
    private String TAG = OkHttpClientFragment.class.getSimpleName();
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");
    private final Moshi moshi = new Moshi.Builder().build();
    private ResponseApi[] responseApis;

    public OkHttpClientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OkHttpClientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OkHttpClientFragment newInstance(String param1, String param2) {
        OkHttpClientFragment fragment = new OkHttpClientFragment();
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
        return inflater.inflate(R.layout.fragment_ok_http_client, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnOkHttp = (Button) view.findViewById(R.id.btnOkhttp);
        btnOkHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    new Thread(new OkHttpClientFragment()).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    private void synchronousGet() {
        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();

        try {
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    Log.d(TAG, "" + responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }
                Log.d(TAG, "" + response.body().string());
            }
        } catch (IOException exp) {
            Log.d(TAG, "" + exp.toString());
        }
    }

    private void asynchronousGet() {
        Request mRequest = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();

        client.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody mResponseBody = response.body()) {
                    if (response.isSuccessful()) {
                        Headers responseHeaders = response.headers();
                        for (int i = 0; i < responseHeaders.size(); i++) {
                            Log.d(TAG, "" + responseHeaders.name(i) + ": " + responseHeaders.value(i));
                        }
                        Log.d(TAG, "" + response.body().string());
                    }
                }
            }
        });
    }

    private void accessingHeader() {
        Request mRequest = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();


        try (Response mResponse = client.newCall(mRequest).execute()) {
            if (mResponse.isSuccessful()) {
                Log.d(TAG, "" + mResponse.header("Server"));
                Log.d(TAG, "" + mResponse.header("Date"));
                Log.d(TAG, "" + mResponse.header("Vary"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void postRequestOkhttp() {
        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";

        Request mRequest = new Request.Builder()
                .url("https://api.github.com/markdown/raw").post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        try (Response mResponse = client.newCall(mRequest).execute()) {
            if (mResponse.isSuccessful()) {
                Log.d(TAG, "" + mResponse.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void postStreamRequestOkhttp() {
        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MEDIA_TYPE_MARKDOWN;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("Numbers\n");
                sink.writeUtf8("-------\n");
                for (int i = 2; i <= 997; i++) {
                    sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                }
            }

            private String factor(int n) {
                for (int i = 2; i < n; i++) {
                    int x = n / i;
                    if (x * i == n) return factor(x) + " × " + i;
                }
                return Integer.toString(n);
            }
        };

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public void postRequestResponseBody() {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody mRequestBody = RequestBody.create(JSON, createJsonObjectForPost());
        Request mRequest = new Request.Builder().url("https://reqres.in/api/users").post(mRequestBody).build();
        try {
            Response mResponse = client.newCall(mRequest).execute();
            if (mResponse.isSuccessful()) {
                Log.d(TAG, "" + mResponse.body().string());
            } else {
                Log.d(TAG, "Failure");
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }

    }

    public void getRequestResponseBody() {
        Request request = new Request.Builder()
                .url("https://api.github.com/gists/c2a7c39532239ff261be")
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String bodyString = response.body().string();

            JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);
            Gist gist = gistJsonAdapter.fromJson(bodyString);

            Log.d(TAG, "" + gist.id);
            Log.d(TAG, "" + gist.url);
            Log.d(TAG, "" + gist.created_at);

        } catch (Exception exp) {
            exp.printStackTrace();
        }

    }

    static class Gist {
        Map<String, GistFile> files;
        String id;
        String url;
        String created_at;
    }

    static class GistFile {
        String content;
    }

    public void getRequestResponseBodyTwo() {
        Request request = new Request.Builder()
                .url("http://www.codemobiles.com/adhoc/feed/youtubes.json")
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String bodyString = response.body().string();
            Log.d(TAG, "" + bodyString);
            Gson gson = new Gson();
            Type type = new TypeToken<Collection<ResponseApi>>() {
            }.getType();
            Collection<ResponseApi> enums = gson.fromJson(bodyString, type);
            responseApis = enums.toArray(new ResponseApi[enums.size()]);
            for (int i = 0; i < responseApis.length; i++) {
                Log.d(TAG, "" + responseApis[i].getDescription());
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }

    }

    public void getRequestResponseBodyThree() {
        Request request = new Request.Builder()
                .url("http://demo5795376.mockable.io/testokhttp")
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String bodyString = response.body().string();

            Menu menu = new Gson().fromJson(bodyString, Menu.class);

            Log.d(TAG, "" + menu.id);
            Log.d(TAG, "" + menu.value);

            List<MenuItem> menuItemList = menu.menuitem;

            for (MenuItem menuItem : menuItemList) {

                Log.d(TAG, "" + menuItem.name);
                Log.d(TAG, "" + menuItem.type);
            }


        } catch (Exception exp) {
            exp.printStackTrace();
        }

    }

    private void postWithParameter() {

        RequestBody formBody = new FormBody.Builder()
                .add("search", "Jurassic Park")
                .build();

        Request mRequest = new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php")
                .post(formBody)
                .build();

        try {
            client.newCall(mRequest).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String bodyString = response.body().string();
                        Log.d(TAG, "" + bodyString);
                    } else {
                        Log.d(TAG, "UnSuccessful");
                    }
                }
            });
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void cancelCall() {

        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        Request mRequest = new Request.Builder()
                .url("http://httpbin.org/delay/2")
                .build();

        final long startNanos = System.nanoTime();
        final Call call = client.newCall(mRequest);


        // Schedule a job to cancel the call in 1 second.
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.printf("%.2f Canceling call.%n", (System.nanoTime() - startNanos) / 1e9f);
                call.cancel();
                System.out.printf("%.2f Canceled call.%n", (System.nanoTime() - startNanos) / 1e9f);
            }
        }, 1, TimeUnit.SECONDS);

        System.out.printf("%.2f Executing call.%n", (System.nanoTime() - startNanos) / 1e9f);
        try (Response response = call.execute()) {
            System.out.printf("%.2f Call was expected to fail, but completed: %s%n",
                    (System.nanoTime() - startNanos) / 1e9f, response);
        } catch (IOException e) {
            System.out.printf("%.2f Call failed as expected: %s%n",
                    (System.nanoTime() - startNanos) / 1e9f, e);
        }

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


    public JSONObject createJsonObject() {
        JSONObject jsonObj = null;
        try {

            jsonObj = new JSONObject();
            jsonObj.put("name", "Arun");
            jsonObj.put("job", "Cab Driver");

            new Gson().toJson("");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }

    public void authenticate() {
        OkHttpClient client = new OkHttpClient.Builder().authenticator(new Authenticator() {
            @Nullable
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                if (response.request().header("Authorization") != null) {
                    return null;
                } else {
                    System.out.println("Authenticating for response: " + response);
                    System.out.println("Challenges: " + response.challenges());
                    String credential = Credentials.basic("jesse", "password1");
                    return response.request().newBuilder().addHeader("Authorization", credential).build();
                }
            }
        }).build();
        try {
            Request request = new Request.Builder()
                    .url("http://publicobject.com/secrets/hellosecret.txt")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                System.out.println(response.body().string());
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void loggingInterceptor() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new LoggingInterceptor())
                .build();

        Request request = new Request.Builder()
                .url("http://www.publicobject.com/helloworld.txt")
                .header("User-Agent", "OkHttp Example").build();

        try {
            Response response = client.newCall(request).execute();
            response.body().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            String srtT1 = String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers());
            Log.i(TAG, "" + srtT1);

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            String srtT2 = String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers());
            Log.i(TAG, "" + srtT2);
            return response;
        }
    }

    @Override
    public void run() {
        loggingInterceptor();
    }


}
