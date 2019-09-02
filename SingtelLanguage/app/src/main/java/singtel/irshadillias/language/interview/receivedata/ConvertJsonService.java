package singtel.irshadillias.language.interview.receivedata;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.irshadillias.singtelservice.IConvertJson;
import com.irshadillias.singtelservice.IConvertJsonLister;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.observers.DisposableObserver;
import singtel.irshadillias.language.interview.data.local.entity.ConvertJsonReponse;
import singtel.irshadillias.language.interview.data.remote.repository.MovieRepository;
import singtel.irshadillias.language.interview.data.remote.webrequest.ConvertJsonRequst;


public class ConvertJsonService extends Service {

    @Inject
    public MovieRepository movieRepository;

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IConvertJson.Stub mBinder = new IConvertJson.Stub() {
        @Override
        public int addNumbers(int num1, int num2) throws RemoteException {
            if(movieRepository == null)
                return 0;
            else
                return num1 + num2+20;
        }

        @Override
        public void convertJson(String txt, IConvertJsonLister  lister) throws RemoteException {
            Log.d("ConvertJsonService","String new :: "+txt);
            getConvertJson(txt,lister);
        }

    };

    public void getConvertJson(String data,IConvertJsonLister  lister) throws RemoteException{
        ConvertJsonRequst req = new ConvertJsonRequst();
        req.setData(data);
        movieRepository.invokeConvertJosn(req).subscribeWith(new DisposableObserver<ConvertJsonReponse>() {
            @Override
            public void onNext(@NonNull ConvertJsonReponse jsonResponse) {
                try {
                    Gson gson = new Gson();
                    String json = gson.toJson(jsonResponse.getData());
                    Log.d("ConvertJsonService",json);
                    lister.success(json);
                }catch (RemoteException exc){
                    Log.e("ConvertJsonService","exception");
                }

            }
            @Override
            public void onError(@NonNull Throwable e) {
                try {
                    lister.success("No result");
                }catch (RemoteException exc){};
            }
            @Override
            public void onComplete() {
            }
        });
    }


}
