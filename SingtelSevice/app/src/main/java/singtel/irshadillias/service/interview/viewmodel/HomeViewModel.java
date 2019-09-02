package singtel.irshadillias.service.interview.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import singtel.irshadillias.service.interview.data.local.entity.ConvertJsonReponse;
import singtel.irshadillias.service.interview.data.local.entity.JsonResult;
import singtel.irshadillias.service.interview.data.remote.repository.SingtelRepository;
import singtel.irshadillias.service.interview.data.remote.webrequest.ConvertJsonRequst;
import singtel.irshadillias.service.interview.utilities.SingleLiveEvent;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<JsonResult> convertJosnResult = new MutableLiveData<>();
    private SingleLiveEvent<Void> errorMessageRecieved = new SingleLiveEvent<>();
    private SingtelRepository singtelrepo;

    @Inject
    HomeViewModel(SingtelRepository artRepository) {
        this.singtelrepo = artRepository;
    }

    public void getConvertJson(String data){
        ConvertJsonRequst req = new ConvertJsonRequst();;;
        req.setData(data);
        singtelrepo.invokeConvertJosn(req).subscribeWith(getMovieDetailObserver());
    }
    private DisposableObserver<ConvertJsonReponse> getMovieDetailObserver(){
        return new DisposableObserver<ConvertJsonReponse>() {
            @Override
            public void onNext(@NonNull ConvertJsonReponse jsonResponse) {
                convertJosnResult.setValue(jsonResponse.getData());
            }
            @Override
            public void onError(@NonNull Throwable e) {
                errorMessageRecieved.call();
            }
            @Override
            public void onComplete() {
            }
        };
    }

    public MutableLiveData<JsonResult> getJonReslt(){
        return convertJosnResult;
    }


}
