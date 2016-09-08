package net.lemonsoft.lemonkit.ui.tip.LKNotification;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.lemonsoft.lemonkit.util.SizeTool;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 1em0nsOft on 16/9/7.
 */
public class LKNotificationBar extends PopupWindow {

    private static final Integer CONTAINER_PADDING = 40;// 通知栏的内边距
    private static final Integer ITEM_SPACING = 30;// 各个控件之间的空隙
    private static final Integer ICON_WIDTH = 120;// 图标控件的宽度
    private static final Integer LINE_HEIGHT = 50;// 行高

    /**
     * 当前控件的contentView
     */
    private RelativeLayout contentLayout;
    /**
     * 容器控件
     */
    private RelativeLayout container;
    /**
     * 图标控件
     */
    private ImageView iconView;
    /**
     * 标题控件
     */
    private TextView titleTextView;
    /**
     * 内容控件
     */
    private TextView contentTextView;
    /**
     * 底部的边框线
     */
    private View bottomLineView;

    /**
     * 保存上下文对象
     */
    private Context context;
    /**
     * 动画的时间，通常该值为0或600
     */
    private Integer animationTime = 600;
    /**
     * 当前通知栏是否显示中
     */
    private boolean isShowing = false;
    /**
     * 隐藏动画的handler
     */
    private HideHandler hideHandler = new HideHandler();
    /**
     * 通知栏所创建在的activity
     */
    private Activity inActivity;
    /**
     * 通知栏被点击的监听器
     */
    private NotificationTouchListener onNotificationBarTouchListener;
    /**
     * 通知栏状态监听器
     */
    private NotificationStateListener notificationStateListener;

    /**
     * 自动关闭时间，默认3000ms
     */
    private Integer autoCloseTime = 3000;

    public LKNotificationBar(Activity inActivity, String title, final String content, Bitmap icon, Style style) {
        super();
        this.inActivity = inActivity;
        this.context = inActivity.getApplicationContext();
        // 根据主题设置背景颜色
        Integer backgroundColor =
                style == Style.STYLE_LIGHT ? Color.argb(230, 255, 255, 255) : Color.argb(230, 0, 0, 0);
        // 根据主题设置文字颜色
        Integer textColor =
                style == Style.STYLE_LIGHT ? Color.argb(200, 0, 0, 0) : Color.argb(200, 255, 255, 255);

        contentLayout = new RelativeLayout(context);
        final RelativeLayout.LayoutParams contentLayoutParams = new RelativeLayout.LayoutParams(
                SizeTool.getScreenWidth(context), RelativeLayout.LayoutParams.WRAP_CONTENT);
        contentLayout.setLayoutParams(contentLayoutParams);
        this.setContentView(contentLayout);

        // 初始化容器view
        container = new RelativeLayout(context);
        final RelativeLayout.LayoutParams containerParams =
                new RelativeLayout.LayoutParams(SizeTool.getScreenWidth(context),
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        container.setLayoutParams(containerParams);
        container.setBackgroundColor(backgroundColor);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShowing) {
                    hide(true);
                    if (onNotificationBarTouchListener != null)
                        onNotificationBarTouchListener.onNotificationTouch(LKNotificationBar.this);
                }
            }
        });

        // 初始化子控件View - 图标
        iconView = new ImageView(context);
        iconView.setImageBitmap(icon);
        iconView.setX(CONTAINER_PADDING);
        iconView.setY(CONTAINER_PADDING + SizeTool.getStatusBarHeight(context));
        RelativeLayout.LayoutParams iconParams =
                new RelativeLayout.LayoutParams(ICON_WIDTH, ICON_WIDTH);
        iconView.setLayoutParams(iconParams);

        // 初始化子控件View - 标题
        titleTextView = new TextView(context);
        titleTextView.setText(title);
        Integer titleX = (int) iconView.getX() + ICON_WIDTH + ITEM_SPACING;
        RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(
                (SizeTool.getScreenWidth(context) - titleX - CONTAINER_PADDING), RelativeLayout.LayoutParams.WRAP_CONTENT);
        titleTextView.setLayoutParams(titleParams);
        titleTextView.setTextColor(textColor);
        titleTextView.setX(titleX);
        titleTextView.setY(iconView.getY());
        titleTextView.setTextSize(18);

        // 初始化子控件View - 内容
        contentTextView = new TextView(context);
        contentTextView.setText(content);
        final Integer contentY = (int) titleTextView.getY() + LINE_HEIGHT + ITEM_SPACING;
        RelativeLayout.LayoutParams contentParams =
                new RelativeLayout.LayoutParams(titleParams.width,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        contentTextView.setLayoutParams(contentParams);
        contentTextView.setTextColor(textColor);
        contentTextView.setX(titleX);
        contentTextView.setY(contentY);

        // 初始化子控件View - 底边线
        bottomLineView = new View(context);
        bottomLineView.setBackgroundColor(Color.argb(180, 150, 150, 150));
        final RelativeLayout.LayoutParams bottomLineParams =
                new RelativeLayout.LayoutParams(SizeTool.getScreenWidth(context), 1);
        bottomLineView.setLayoutParams(bottomLineParams);

        // 把子控件添加到容器中
        container.addView(this.iconView);
        container.addView(this.titleTextView);
        container.addView(this.contentTextView);
        container.addView(this.bottomLineView);

        contentLayout.addView(container);

        contentTextView.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        contentLayout.getLayoutParams().height =
                                contentTextView.getHeight() + contentY + CONTAINER_PADDING;
                        containerParams.height = contentLayout.getLayoutParams().height;
                        bottomLineView.setY(container.getHeight() - 1);
                        TranslateAnimation startAnimation = new TranslateAnimation(0, 0, -containerParams.height, 0);
                        startAnimation.setDuration(animationTime);
                        startAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                                if (notificationStateListener != null && isShowing)
                                    notificationStateListener.onStartShow();
                                isShowing = true;
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                if (notificationStateListener != null)
                                    notificationStateListener.onShowComplete();
                                new Timer().schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        if (isShowing)// 当前现实中，自动关闭
                                            hideHandler.sendEmptyMessage(0);
                                    }
                                }, autoCloseTime);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        container.startAnimation(startAnimation);
                    }
                });

        this.setWidth(SizeTool.getScreenWidth(context));// 设置当前控件的宽度为屏幕宽
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 隐藏的handler
     */
    class HideHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            hide(true);
        }
    }

    /**
     * 设置导航栏的透明度
     *
     * @param alpha 透明度数值
     */
    public void setBarAlpha(Integer alpha) {
        this.container.setAlpha(alpha);
    }

    /**
     * 在指定Activity中显示通知栏
     *
     * @param animated 是否动画显示
     */
    public void show(boolean animated) {
        calAnimationTime(animated);
        showAtLocation(inActivity.findViewById(android.R.id.content), Gravity.TOP, 0, 0);
    }

    /**
     * 动画显示通知栏
     */
    public void show() {
        show(true);
    }

    public void show(NotificationStateListener listener) {
        notificationStateListener = listener;
        show();
    }

    /**
     * 隐藏关闭当前通知栏
     *
     * @param animated 是否动画隐藏
     */
    public void hide(boolean animated) {
        calAnimationTime(animated);
        isShowing = false;
        TranslateAnimation endTranslateAnimation =
                new TranslateAnimation(0, 0, 0, -contentLayout.getLayoutParams().height);
        endTranslateAnimation.setDuration(animationTime);
        endTranslateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (notificationStateListener != null)
                    notificationStateListener.onStartHide();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (notificationStateListener != null)
                    notificationStateListener.onHideComplete();
                dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        container.startAnimation(endTranslateAnimation);
    }

    /**
     * 根据是否加载动画来计算动画时间
     *
     * @param animated 是否加载动画
     */
    public void calAnimationTime(boolean animated) {
        this.animationTime = animated ? 600 : 0;
    }

    /**
     * 设置自动关闭的时间
     *
     * @param autoCloseTime 设置自动关闭的时间
     */
    public void setAutoCloseTime(Integer autoCloseTime) {
        this.autoCloseTime = autoCloseTime;
    }

    /**
     * 获取当前自动关闭的时间
     *
     * @return 当前自动关闭的时间
     */
    public Integer getAutoCloseTime() {
        return autoCloseTime;
    }

    /**
     * 通知栏的样式主题枚举
     */
    public enum Style {
        STYLE_LIGHT(0),// 亮主题
        STYLE_DARK(1);// 暗主题

        private Integer code;

        public Integer getCode() {
            return code;
        }

        private Style(Integer code) {
            this.code = code;
        }
    }

    /**
     * 通知栏被点击的监听器
     */
    public interface NotificationTouchListener {
        void onNotificationTouch(LKNotificationBar bar);
    }

    public interface NotificationStateListener {
        // 开始启动
        void onStartShow();

        // 启动完毕
        void onShowComplete();

        // 开始隐藏
        void onStartHide();

        // 隐藏完毕
        void onHideComplete();
    }

    public NotificationTouchListener getOnNotificationBarTouchListener() {
        return onNotificationBarTouchListener;
    }

    public void setOnNotificationBarTouchListener(NotificationTouchListener onNotificationBarTouchListener) {
        this.onNotificationBarTouchListener = onNotificationBarTouchListener;
    }

    public String getTitle() {
        return titleTextView.getText().toString();
    }

    public String getContent() {
        return contentTextView.getText().toString();
    }

    public Bitmap getIcon() {
        return iconView.getDrawingCache();
    }

}
