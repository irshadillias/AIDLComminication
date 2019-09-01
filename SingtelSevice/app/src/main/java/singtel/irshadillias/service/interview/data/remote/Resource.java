package singtel.irshadillias.service.interview.data.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Resource <T> {
    @NonNull
    public final HttpStatus status;

    @Nullable
    public final T data;

    @Nullable private final String message;

    private Resource(@NonNull HttpStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(HttpStatus.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(HttpStatus.ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(HttpStatus.LOADING, data, null);
    }

    @Nullable
    public String getMessage() {
        return message;
    }
}