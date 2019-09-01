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

public interface WebApiServices {
    @POST("post")
    Observable<ConvertJsonReponse> getJsonResult(@Body ConvertJsonRequst jsonInput);
}
