package singtel.irshadillias.service.interview.data.remote;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import singtel.irshadillias.service.interview.data.local.entity.ConvertJsonReponse;
import singtel.irshadillias.service.interview.data.remote.webrequest.ConvertJsonRequst;
import singtel.irshadillias.service.interview.data.remote.webrequest.SingtelCalRequest;

public interface WebApiServices {
    @POST("post")
    Observable<ConvertJsonReponse> getJsonResult(@Body ConvertJsonRequst jsonInput);

    @POST("post")
    Observable<ConvertJsonReponse> getAdd(@Body SingtelCalRequest singtelAdd);

    @POST("post")
    Observable<ConvertJsonReponse> getSubstract(@Body SingtelCalRequest singtelSub);

    @POST("post")
    Observable<ConvertJsonReponse> getMultiply(@Body SingtelCalRequest singtelMulti);

    @POST("post")
    Observable<ConvertJsonReponse> getPow(@Body SingtelCalRequest singtelPow);
}
