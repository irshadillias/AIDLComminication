package singtel.irshadillias.language.interview.utilities;

import android.util.Log;

import singtel.irshadillias.language.interview.BuildConfig;

class Logs {
    private static final boolean ENABLE_LOGS = BuildConfig.DEBUG;

    private Logs() {
    }

    @SuppressWarnings("unused")
    public static void v(String tag, String msg) {
        if (ENABLE_LOGS) {
            Log.v(tag, msg);
        }
    }

    @SuppressWarnings("unused")
    public static void v(String tag, String msg, Exception e) {
        if (ENABLE_LOGS) {
            Log.v(tag, msg, e);
        }
    }

    @SuppressWarnings("unused")
    public static void v(String tag, String msg, OutOfMemoryError e) {
        if (ENABLE_LOGS) {
            Log.v(tag, msg, e);
        }
    }

    @SuppressWarnings("unused")
    public static boolean getIsLogsEnabled() {
        return ENABLE_LOGS;
    }

    @SuppressWarnings("unused")
    public static void reportException(Exception e) {
        if (ENABLE_LOGS) {
            Log.e("Exception", e.toString(), e);
        }
    }
}
