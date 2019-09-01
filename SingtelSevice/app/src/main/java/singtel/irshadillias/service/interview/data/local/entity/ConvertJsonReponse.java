package singtel.irshadillias.service.interview.data.local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConvertJsonReponse implements Parcelable {
    @SerializedName("json")
    @Expose
    JsonResult data;

    protected ConvertJsonReponse(Parcel in) {
        data = in.readParcelable(JsonResult.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(data, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ConvertJsonReponse> CREATOR = new Creator<ConvertJsonReponse>() {
        @Override
        public ConvertJsonReponse createFromParcel(Parcel in) {
            return new ConvertJsonReponse(in);
        }

        @Override
        public ConvertJsonReponse[] newArray(int size) {
            return new ConvertJsonReponse[size];
        }
    };

    public JsonResult getData() {
        return data;
    }

    public void setData(JsonResult data) {
        this.data = data;
    }
}
