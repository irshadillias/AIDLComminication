package singtel.irshadillias.service.interview.data.remote.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;
import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Retrofit;
import singtel.irshadillias.service.interview.data.local.dao.MovieDao;
import singtel.irshadillias.service.interview.data.local.entity.ConvertJsonReponse;
import singtel.irshadillias.service.interview.data.remote.WebApiServices;
import singtel.irshadillias.service.interview.data.remote.webrequest.ConvertJsonRequst;
import singtel.irshadillias.service.interview.data.remote.webrequest.SingtelCalRequest;

public class SingtelRepository {

    private final WebApiServices apiService;
    private final MovieDao movieDao;
    private final Retrofit rectrofit;

    @Inject
    SingtelRepository(MovieDao dao, WebApiServices service, Retrofit rectrofitVal) {
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

    @SuppressLint("CheckResult")
    public Observable<ConvertJsonReponse> invokeadd(SingtelCalRequest singtelAdd){
        return rectrofit.create(WebApiServices.class)
                .getAdd (singtelAdd)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @SuppressLint("CheckResult")
    public Observable<ConvertJsonReponse> invokeSubstract(SingtelCalRequest singtelSub){
        return rectrofit.create(WebApiServices.class)
                .getSubstract (singtelSub)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }


    @SuppressLint("CheckResult")
    public Observable<ConvertJsonReponse> invokeMultipy(SingtelCalRequest singtelMultipy){
        return rectrofit.create(WebApiServices.class)
                .getMultiply (singtelMultipy)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }


    @SuppressLint("CheckResult")
    public Observable<ConvertJsonReponse> invokepow(SingtelCalRequest pow){
        return rectrofit.create(WebApiServices.class)
                .getPow (pow)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }
}
