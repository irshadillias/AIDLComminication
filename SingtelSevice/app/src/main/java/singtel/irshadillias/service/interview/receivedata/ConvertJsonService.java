package singtel.irshadillias.service.interview.receivedata;

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
import singtel.irshadillias.service.interview.data.local.entity.ConvertJsonReponse;
import singtel.irshadillias.service.interview.data.remote.repository.SingtelRepository;
import singtel.irshadillias.service.interview.data.remote.webrequest.ConvertJsonRequst;
import singtel.irshadillias.service.interview.data.remote.webrequest.SingtelCalRequest;


public class ConvertJsonService extends Service {

    @Inject
    public SingtelRepository singtelRepository;

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
            if(singtelRepository == null)
                return 0;
            else
                return num1 + num2+20;
        }

        @Override
        public void convertJson(String txt, IConvertJsonLister  lister) throws RemoteException {
            Log.d("ConvertJsonService","String new :: "+txt);
            getConvertJson(txt,lister);
        }

        @Override
        public void addNumber(int num1, int num2, IConvertJsonLister lister) throws RemoteException {
            SingtelCalRequest reqest = new SingtelCalRequest();
            reqest.setNum1(Integer.toString(num1));
            reqest.setNum2(Integer.toString(num2));
            //Web API not hosted for publically using local changes
            // getAddNumber(reqest,lister);
            lister.success(Integer.toString(num1+num2));

        }

        @Override
        public void substract(int num1, int num2, IConvertJsonLister lister) throws RemoteException {
            SingtelCalRequest reqest = new SingtelCalRequest();
            reqest.setNum1(Integer.toString(num1));
            reqest.setNum2(Integer.toString(num2));
            //Web API not hosted for publically using local changes
            //getSubstract(reqest,lister);
            lister.success(Integer.toString(num1-num2));
        }

        @Override
        public void multiply(int num1, int num2, IConvertJsonLister lister) throws RemoteException {
            SingtelCalRequest reqest = new SingtelCalRequest();
            reqest.setNum1(Integer.toString(num1));
            reqest.setNum2(Integer.toString(num2));
            //Web API not hosted for publically using local changes
            //getMultiply(reqest,lister);
            lister.success(Integer.toString(num1*num2));
        }

        @Override
        public void pow(int num1,int num2, IConvertJsonLister lister) throws RemoteException {
            SingtelCalRequest reqest = new SingtelCalRequest();
            reqest.setNum1(Integer.toString(num1));
            reqest.setNum2(Integer.toString(num2));
            //Web API not hosted for publically using local changes
            //getPow(reqest,lister);
            lister.success(Double.toString(Math.pow(num1, num2)));
        }


    };

    public void getConvertJson(String data,IConvertJsonLister  lister) throws RemoteException{
        ConvertJsonRequst req = new ConvertJsonRequst();
        req.setData(data);
        singtelRepository.invokeConvertJosn(req).subscribeWith(new DisposableObserver<ConvertJsonReponse>() {
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

    public void getAddNumber(SingtelCalRequest request, IConvertJsonLister  lister) throws RemoteException{
        singtelRepository.invokeadd(request).subscribeWith(new DisposableObserver<ConvertJsonReponse>() {
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


    public void getSubstract(SingtelCalRequest request,IConvertJsonLister  lister) throws RemoteException{

        singtelRepository.invokeSubstract(request).subscribeWith(new DisposableObserver<ConvertJsonReponse>() {
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


    public void getMultiply(SingtelCalRequest req,IConvertJsonLister  lister) throws RemoteException{

        singtelRepository.invokeMultipy(req).subscribeWith(new DisposableObserver<ConvertJsonReponse>() {
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

    public void getPow(SingtelCalRequest req,IConvertJsonLister  lister) throws RemoteException{

        singtelRepository.invokepow(req).subscribeWith(new DisposableObserver<ConvertJsonReponse>() {
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
