package singtel.irshadillias.language.interview.receivedata.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Result implements Parcelable {
    public String result;
    public String errorCode;
    public String errorMessage;

    public Result(){

    }

    protected Result(Parcel in) {
        result = in.readString();
        errorCode = in.readString();
        errorMessage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(result);
        dest.writeString(errorCode);
        dest.writeString(errorMessage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
}
