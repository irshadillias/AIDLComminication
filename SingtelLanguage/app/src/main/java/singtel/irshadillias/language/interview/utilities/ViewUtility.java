package singtel.irshadillias.language.interview.utilities;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.IntDef;
import android.view.Display;
import android.view.WindowManager;

public class ViewUtility {
    public static final int TRANSITION_POP = 0;
    public static final int TRANSITION_FADE_IN_OUT = 1;
    public static final int TRANSITION_SLIDE_LEFT_RIGHT = 2;
    public static final int TRANSITION_SLIDE_LEFT_RIGHT_WITHOUT_EXIT = 3;
    public static final int TRANSITION_NONE = 4;

    @IntDef({TRANSITION_POP, TRANSITION_FADE_IN_OUT, TRANSITION_SLIDE_LEFT_RIGHT,
            TRANSITION_SLIDE_LEFT_RIGHT_WITHOUT_EXIT, TRANSITION_NONE})
    public @interface FragmentAnimation {}

    public static int getScreenWidth(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService("window");
        Display display = wm.getDefaultDisplay();
        int width1;
        if (Build.VERSION.SDK_INT > 12) {
            Point size = new Point();
            display.getSize(size);
            width1 = size.x;
        } else {
            width1 = display.getWidth();
        }

        return width1;
    }

    public static int getScreenHeight(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService("window");
        Display display = wm.getDefaultDisplay();
        int height1;
        if (Build.VERSION.SDK_INT > 12) {
            Point size = new Point();
            display.getSize(size);
            height1 = size.y;
        } else {
            height1 = display.getHeight();
        }

        return height1;
    }


    public static int getAspectWidth(Context context,
                                     int width) {
        int screenWidth = getScreenWidth(context);

        Double aspectWidth = (double) ((width * 100) / screenWidth);
        Double newWidth = Math.ceil((aspectWidth * screenWidth) / 100);

        return newWidth.intValue();
    }


    public static int getAspectHeight(Context context,
                                      int height) {
        int screenHeight = getScreenHeight(context);

        Double aspecHeight = (double) ((height * 100) / screenHeight);
        Double newHeight = Math.ceil((aspecHeight * screenHeight) / 100);

        return newHeight.intValue();
    }
}
