package singtel.irshadillias.service.interview.utilities;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BaseUtils {



    private static String getJSONStringFromRaw(Context context, int rawId) {
        InputStream content = context.getResources().openRawResource(rawId);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
        String respString = "";
        try {
            String s = "";
            while ((s = buffer.readLine()) != null) {
                respString += s;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respString;
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
        int screenWidth = ViewUtility.getScreenWidth(context);

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

    public static String getYear(String dateString) {
        Date date = getDate(dateString);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return ""+cal.get(Calendar.YEAR);
    }


    private static Date getDate(String aDate) {
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;

    }
}
