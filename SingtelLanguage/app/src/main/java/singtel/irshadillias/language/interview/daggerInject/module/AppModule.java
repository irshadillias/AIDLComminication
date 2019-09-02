package singtel.irshadillias.language.interview.daggerInject.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import singtel.irshadillias.language.interview.data.local.MovieDatabase;
import singtel.irshadillias.language.interview.data.local.dao.MovieDao;
import singtel.irshadillias.language.interview.data.remote.ApiConstants;
import singtel.irshadillias.language.interview.data.remote.RequestInterceptor;
import singtel.irshadillias.language.interview.data.remote.WebApiServices;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.addInterceptor(new RequestInterceptor());
        okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    WebApiServices provideWebApi(Retrofit retrofit) {
        return retrofit.create(WebApiServices.class);
    }

    @Provides
    @Singleton
    Retrofit provideRectrofitValue(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }
    @Provides
    @Singleton
    MovieDatabase provideMovieDatabase(Application application) {
        return Room.databaseBuilder(application, MovieDatabase.class, "movies.db").build();
    }

    @Provides
    @Singleton
    MovieDao provideArticleDao(MovieDatabase articleDatabase) {
        return articleDatabase.articleDao();
    }



}
