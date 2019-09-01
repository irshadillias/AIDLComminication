package singtel.irshadillias.service.interview.daggerInject.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import singtel.irshadillias.service.interview.views.fragment.SigtelServerHome;

@Module
public abstract class FragmentBuilderModule {
    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract SigtelServerHome contributeSigtelHome();

}
