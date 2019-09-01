package singtel.irshadillias.service.interview.daggerInject.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import singtel.irshadillias.service.interview.SingtelApp;
import singtel.irshadillias.service.interview.daggerInject.builder.ActivityBuilderModule;
import singtel.irshadillias.service.interview.daggerInject.builder.ServiceBuilderModule;
import singtel.irshadillias.service.interview.daggerInject.module.AppModule;

@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class,
        ServiceBuilderModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(SingtelApp nyTimesApp);
}
