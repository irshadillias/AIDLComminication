package singtel.irshadillias.language.interview.data.remote;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import singtel.irshadillias.language.interview.data.local.entity.ConvertJsonReponse;
import singtel.irshadillias.language.interview.data.remote.webrequest.ConvertJsonRequst;

public interface WebApiServices {
    @POST("post")
    Observable<ConvertJsonReponse> getJsonResult(@Body ConvertJsonRequst jsonInput);
}
