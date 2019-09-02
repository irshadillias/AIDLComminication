package singtel.irshadillias.language.interview.data.remote.repository;

import android.annotation.SuppressLint;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import singtel.irshadillias.language.interview.data.local.dao.MovieDao;
import singtel.irshadillias.language.interview.data.local.entity.ConvertJsonReponse;
import singtel.irshadillias.language.interview.data.remote.WebApiServices;
import singtel.irshadillias.language.interview.data.remote.webrequest.ConvertJsonRequst;

public class MovieRepository {

    private final WebApiServices apiService;
    private final MovieDao movieDao;
    private final Retrofit rectrofit;

    @Inject
    MovieRepository(MovieDao dao, WebApiServices service, Retrofit rectrofitVal) {
        this.apiService = service;
        this.movieDao   = dao;
        this.rectrofit  = rectrofitVal;
    }

    @SuppressLint("CheckResult")
    public Observable<ConvertJsonReponse> invokeConvertJosn(ConvertJsonRequst jonInput){
        return rectrofit.create(WebApiServices.class)
                .getJsonResult (jonInput)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }
}
