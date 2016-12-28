package net.lemonsoft.lemonkit.core_tool;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * LemonKit工具类 - 尺寸相关工具
 * Created by LiuRi on 2016/12/23.
 */

public class LKSizeTool {

    private float _density;
    private DisplayMetrics _metrics;

    private static LKSizeTool _privateSizeTool;

    public static synchronized LKSizeTool getPrivateSizeTool() {
        if (_privateSizeTool == null)
            _privateSizeTool = new LKSizeTool();
        return _privateSizeTool;
    }

    public void setContext(Context context) {
        _density = context.getResources().getDisplayMetrics().density;
        _metrics = new DisplayMetrics();
        ((WindowManager) (context.getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay().getMetrics(_metrics);
    }

    /**
     * 换算dp到px
     *
     * @param dpValue dp的数值
     * @return 对应的px数值
     */
    public int dpToPx(int dpValue) {
        return (int) (_density * dpValue + 0.5f);
    }

    /**
     * 换算px到dp
     *
     * @param pxValue px的数值
     * @return 对应的dp数值
     */
    public int pxToDp(int pxValue) {
        return (int) (pxValue / _density + 0.5f);
    }

    /**
     * 换算dp到px
     *
     * @param dpValue dp的数值
     * @return 对应的px数值
     */
    public int dpToPx(float dpValue) {
        return (int) (_density * dpValue + 0.5f);
    }

    /**
     * 换算px到dp
     *
     * @param pxValue px的数值
     * @return 对应的dp数值
     */
    public int pxToDp(float pxValue) {
        return (int) (pxValue / _density + 0.5f);
    }

    /**
     * DP值，实际就是把数值当做DP来换算成PX返回
     *
     * @param dpValue DP的值
     * @return
     */
    public int DP(float dpValue) {
        return pxToDp(dpValue);
    }

    /**
     * DP值，实际就是把数值当做DP来换算成PX返回
     *
     * @param dpValue DP的值
     * @return
     */
    public int DP(int dpValue) {
        return pxToDp(dpValue);
    }

    /**
     * 获取屏幕的宽，单位dp
     *
     * @return 屏幕宽度dp值
     */
    public int screenWidthDp() {
        return pxToDp(_metrics.widthPixels);
    }

    /**
     * 获取屏幕的高，单位dp
     *
     * @return 屏幕高度的dp值
     */
    public int screenHeightDp() {
        return pxToDp(_metrics.heightPixels);
    }

}
