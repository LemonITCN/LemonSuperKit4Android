package net.lemonsoft.lemonkit.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 工具类 - 尺寸相关
 * Created by 1em0nsOft on 16/9/7.
 */
public class SizeTool {

    private static WindowManager manager;
    private static DisplayMetrics displayMetrics;

    private static void initManager(Context context) {
        if (manager == null)
            manager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        if (displayMetrics == null) {
            displayMetrics = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(displayMetrics);
        }
    }

    /**
     * 获取屏幕的宽度
     *
     * @param context 上下文对象
     * @return 当前手机的屏幕宽度
     */
    public static Integer getScreenWidth(Context context) {
        initManager(context);
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕的高度
     *
     * @param context 上下文对象
     * @return 当前手机的屏幕高度
     */
    public static Integer getScreenHeight(Context context) {
        initManager(context);
        return displayMetrics.heightPixels;
    }

    /**
     * 获取当前手机的状态栏的高度
     *
     * @param context 上下文对象
     * @return 当前手机的状态栏的高度
     */
    public static Integer getStatusBarHeight(Context context) {
        Integer height = 0;
        Integer resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            height = context.getResources().getDimensionPixelSize(resourceId);
        return height;
    }

}
