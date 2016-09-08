package net.lemonsoft.lemonkit.ui.tip.LKNotification;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * LK通知的管理器
 * Created by 1em0nsOft on 16/9/8.
 */
public class LKNotification {

    /**
     * 默认的图标
     */
    private static Bitmap default_icon;
    /**
     * 默认的主题
     */
    private static LKNotificationBar.Style default_style = LKNotificationBar.Style.STYLE_LIGHT;
    /**
     * 默认的透明度
     */
    private static Integer default_alpha = 230;
    /**
     * 默认的监听器
     */
    private static LKNotificationBar.NotificationTouchListener default_listener;

    /**
     * 显示LK通知，并监听通知栏点击事件
     *
     * @param inActivity 要显示在的Activity
     * @param title      通知标题
     * @param content    通知内容
     * @param icon       通知图标
     * @param listener   通知的监听器
     */
    public static void show(Activity inActivity, String title, String content, Bitmap icon, LKNotificationBar.NotificationTouchListener listener) {
        LKNotificationBar bar = new LKNotificationBar(inActivity, title, content, icon, default_style);
        bar.setOnNotificationBarTouchListener(listener);
        bar.show(true);
    }

    /**
     * 显示LK通知，并监听通知栏点击事件
     *
     * @param inActivity           要显示在的Activity
     * @param title                通知标题
     * @param content              通知内容
     * @param iconDrawableResultId 通知图标的资源ID
     * @param listener             通知的监听器
     */
    public static void show(Activity inActivity, String title, String content, Integer iconDrawableResultId, LKNotificationBar.NotificationTouchListener listener) {
        show(inActivity, title, content, BitmapFactory.decodeResource(inActivity.getResources(), iconDrawableResultId), listener);
    }

    /**
     * 显示LK通知
     *
     * @param inActivity 要显示在的Activity
     * @param title      通知标题
     * @param content    通知内容
     * @param icon       通知图标
     */
    public static void show(Activity inActivity, String title, String content, Bitmap icon) {
        LKNotificationBar bar = new LKNotificationBar(inActivity, title, content, icon, default_style);
        bar.setBarAlpha(default_alpha);
        if (default_listener != null)
            bar.setOnNotificationBarTouchListener(default_listener);
        bar.show(true);
    }

    /**
     * 显示LK通知
     *
     * @param inActivity           要显示在的Activity
     * @param title                通知标题
     * @param content              通知内容
     * @param iconDrawableResultId 通知图标的资源ID
     */
    public static void show(Activity inActivity, String title, String content, Integer iconDrawableResultId) {
        show(inActivity, title, content, BitmapFactory.decodeResource(inActivity.getResources(), iconDrawableResultId));
    }

    /**
     * 显示LK通知，使用默认的图标
     *
     * @param inActivity 要显示在的Activity
     * @param title      通知标题
     * @param content    通知内容
     */
    public static void show(Activity inActivity, String title, String content) {
        if (default_icon != null)
            show(inActivity, title, content, default_icon);
        else
            System.out.println("You haven't set the default icon.");
    }

    /**
     * 获取现在默认的通知图标
     *
     * @return 默认的通知图标
     */
    public static Bitmap getDefault_icon() {
        return default_icon;
    }

    /**
     * 设置默认的通知图标
     *
     * @param default_icon 默认的通知图标
     */
    public static void setDefault_icon(Bitmap default_icon) {
        LKNotification.default_icon = default_icon;
    }

    /**
     * 通过资源ID设置默认的通知图标
     *
     * @param context                     上下文对象
     * @param defaultIconDrawableResultId 默认的通知图标的资源id
     */
    public static void setDefault_icon(Context context, Integer defaultIconDrawableResultId) {
        LKNotification.default_icon =
                BitmapFactory.decodeResource(context.getResources(), defaultIconDrawableResultId);
    }

    /**
     * 获取当前默认的主题
     *
     * @return 默认的主题
     */
    public static LKNotificationBar.Style getDefault_style() {
        return default_style;
    }

    /**
     * 设置默认的主题
     *
     * @param default_style 默认的主题样式
     */
    public static void setDefault_style(LKNotificationBar.Style default_style) {
        LKNotification.default_style = default_style;
    }

    /**
     * 获取默认的通知栏透明度
     *
     * @return 默认的通知栏透明度数值
     */
    public static Integer getDefault_alpha() {
        return default_alpha;
    }

    /**
     * 设置默认的通知栏透明度
     *
     * @param default_alpha 要设置的默认透明度数值 0-255
     */
    public static void setDefault_alpha(Integer default_alpha) {
        LKNotification.default_alpha = default_alpha;
    }

}
