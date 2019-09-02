package singtel.irshadillias.language.interview.daggerInject.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import singtel.irshadillias.language.interview.receivedata.ConvertJsonService;

@Module
public abstract class ServiceBuilderModule {
    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract ConvertJsonService contributeJsonService();
}
