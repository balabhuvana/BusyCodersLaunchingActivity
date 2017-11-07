package learning.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import learning.dagger.MyDaggerFragment;
import learning.dagger.modules.NetworkModule;
import learning.dagger.modules.StorageModule;

/**
 * Created by vivz on 11/09/15.
 */
@Singleton
@Component(modules = {StorageModule.class, NetworkModule.class})
public interface StorageComponent {
    void inject(MyDaggerFragment myDaggerFragment);
}
