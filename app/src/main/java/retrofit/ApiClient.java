package retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bala on 16/10/17.
 */

public class ApiClient {
    public static final String BASE_URL_ONE = "http://api.themoviedb.org/3/";
    public static final String BASE_URL_TWO = "https://git.eclipse.org/r/";
    public static final String BASE_URL_THREE = "http://demo5795376.mockable.io/";
    public static final String BASE_URL_FOUR = "http://www.codemobiles.com/";
    public static final String BASE_URL_FIVE = "https://reqres.in/";


    public static Retrofit mRetrofit = null;

    public static Retrofit getClient() {
        if (mRetrofit == null) {
            Gson gson = new GsonBuilder().setLenient().create();
            mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL_ONE).addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return mRetrofit;
    }

}
