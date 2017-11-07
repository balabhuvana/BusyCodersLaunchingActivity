package learning.dagger;

import android.app.Application;

import learning.dagger.component.DaggerStorageComponent;
import learning.dagger.component.StorageComponent;
import learning.dagger.modules.NetworkModule;
import learning.dagger.modules.StorageModule;

/**
 * Created by vivz on 11/09/15.
 */
public class MyApplication extends Application {

    StorageComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerStorageComponent
                .builder()
                .storageModule(new StorageModule(this))
                .networkModule(new NetworkModule(this))
                .build();
    }

    public StorageComponent getComponent() {
        return component;
    }
}