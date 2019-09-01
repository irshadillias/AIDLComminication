package singtel.irshadillias.service.interview.daggerInject.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import singtel.irshadillias.service.interview.views.activity.LaucherActivity;

@Module
public abstract class ActivityBuilderModule {
    @SuppressWarnings("unused")
    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract LaucherActivity mainActivity();

}
