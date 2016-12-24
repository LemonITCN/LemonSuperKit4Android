package net.lemonsoft.lemonkit.util;

import android.app.Activity;
import android.os.Build;
import android.view.WindowManager;

/**
 * Created by lemonsoft on 16-9-22.
 */

public class ActivityUtil {

    public static boolean isTranslucentStatusBar(Activity activity){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS &
                activity.getWindow().getAttributes().flags) ==
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
    }

}
