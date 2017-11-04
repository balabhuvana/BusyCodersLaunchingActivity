package service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import fragments.MyServiceFragment;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
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

    private String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent");

        if (intent != null) {
            final String action = intent.getAction();
            Log.d(TAG, "onHandleIntent - " + action);
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                Log.d(TAG, "" + param1);
                Log.d(TAG, "" + param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                Log.d(TAG, "" + param1);
                Log.d(TAG, "" + param2);
            } else if (ACTION_DOWNLOAD.equals(action)) {
                Log.d(TAG, "" + intent.getStringExtra(EXTRA_URL));

                try {
                    String URL = intent.getStringExtra(EXTRA_URL);
                    OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
                    Request request = new Request.Builder().url(URL).build();
                    Response response = okHttpClient.newCall(request).execute();
                    String responseString = response.body().string();

                    Intent broadcastIntent = new Intent();
                    broadcastIntent.setAction(MyServiceFragment.MySampleBroadCast.PROCESS_RESPONSE);
                    broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
                    broadcastIntent.putExtra(DOWNLOADED_RESPONSE, responseString);
                    sendBroadcast(broadcastIntent);
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }


}
