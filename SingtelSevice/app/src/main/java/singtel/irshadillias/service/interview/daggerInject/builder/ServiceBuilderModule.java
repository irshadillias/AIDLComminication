package singtel.irshadillias.service.interview.daggerInject.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import singtel.irshadillias.service.interview.receivedata.ConvertJsonService;

@Module
public abstract class ServiceBuilderModule {
    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract ConvertJsonService contributeJsonService();
}
