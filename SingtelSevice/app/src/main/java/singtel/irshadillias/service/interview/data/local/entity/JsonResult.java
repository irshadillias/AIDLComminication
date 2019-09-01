package singtel.irshadillias.service.interview.data.local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonResult implements Parcelable {

    @SerializedName("data")
    @Expose
    String data;

    protected JsonResult(Parcel in) {
        data = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<JsonResult> CREATOR = new Creator<JsonResult>() {
        @Override
        public JsonResult createFromParcel(Parcel in) {
            return new JsonResult(in);
        }

        @Override
        public JsonResult[] newArray(int size) {
            return new JsonResult[size];
        }
    };

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
