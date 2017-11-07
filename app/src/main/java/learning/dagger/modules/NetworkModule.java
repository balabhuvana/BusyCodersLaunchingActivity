package learning.dagger.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import learning.dagger.MyApplication;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bala on 7/11/17.
 */
@Module
public class NetworkModule {
    public static final String BASE_URL_ONE = "http://api.themoviedb.org/3/";
    public static Retrofit mRetrofit;
    private final MyApplication application;

    public NetworkModule(MyApplication application) {
        this.application = application;
        if (mRetrofit == null) {
            Gson gson = new GsonBuilder().setLenient().create();
            mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL_ONE).addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
    }

    @Singleton
    @Provides
    public Retrofit getmRetrofitObject() {
        return mRetrofit;
    }
}
