package singtel.irshadillias.language.interview;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.ice.restring.Restring;
import com.ice.restring.RestringConfig;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import singtel.irshadillias.language.interview.daggerInject.components.DaggerAppComponent;
import singtel.irshadillias.language.interview.utilities.LocaleManager;
import singtel.irshadillias.language.interview.utilities.SampleStringsLoader;

public class SingtelApp extends Application implements HasActivityInjector, HasServiceInjector {

    private static SingtelApp sInstance;
    public static LocaleManager localeManager;


    public static SingtelApp getAppContext() {
        return sInstance;
    }



    private static synchronized void setInstance(SingtelApp app) {
        sInstance = app;
    }

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Inject
    DispatchingAndroidInjector<Service> dispatchingServiceInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        setInstance(this);
    }

    private void initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        Restring.init(this,
                new RestringConfig.Builder()
                        .persist(true)
                        .stringsLoader(new SampleStringsLoader())
                        .build()
        );
    }

    @Override
    protected void attachBaseContext(Context base) {
        localeManager = new LocaleManager(base);
        super.attachBaseContext(localeManager.setLocale(base));

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        localeManager.setLocale(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return dispatchingServiceInjector;
    }
}

