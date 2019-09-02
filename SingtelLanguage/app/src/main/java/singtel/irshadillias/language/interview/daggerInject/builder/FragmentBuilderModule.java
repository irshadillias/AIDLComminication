package singtel.irshadillias.language.interview.daggerInject.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import singtel.irshadillias.language.interview.views.fragment.SigtelServerHome;
import singtel.irshadillias.language.interview.views.fragment.SingtelfragmentArticle;

@Module
public abstract class FragmentBuilderModule {
    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract SigtelServerHome contributeSigtelHome();


    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract SingtelfragmentArticle contributeSigtelArticle();


}
