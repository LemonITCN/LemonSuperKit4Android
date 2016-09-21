package net.lemonsoft.lemonkit.ui.tip.LKActionSheet;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import net.lemonsoft.lemonkit.util.SizeTool;

/**
 * ActionSheet视图
 * Created by 1em0nsOft on 2016/9/21.
 */
public class LKActionSheetView extends PopupWindow {

    private RelativeLayout contentLayout;// 内容布局
    private ImageView screenShotImageView;// 背景图
    private RelativeLayout actionSheetLayout;// actionSheet主内容布局

    private static LKActionSheetView defaultActionSheetObj;

    public static LKActionSheetView defaultActionSheet() {
        if (defaultActionSheetObj == null)
            defaultActionSheetObj = new LKActionSheetView();
        return defaultActionSheetObj;
    }

    public void show1(Activity activity, ImageView imageView) {
        // 计算屏幕的宽度和高度
        Integer screenWidth = SizeTool.getScreenWidth(activity);
        Integer screenHeight = SizeTool.getScreenHeight(activity);
        View activityRootView = activity.findViewById(android.R.id.content);
        // 刷新绘制Activity
        activityRootView.invalidate();
        // 设置整个popupwindow为和屏幕一样宽高
        this.setWidth(screenWidth);
        this.setHeight(screenHeight);
        // 设置内容布局
        this.contentLayout = new RelativeLayout(activity);
        this.contentLayout.setBackgroundColor(Color.BLACK);
        // 设置内容布局的大小
        final RelativeLayout.LayoutParams contentLayoutParams = new RelativeLayout.LayoutParams(
                SizeTool.getScreenWidth(activity), SizeTool.getScreenHeight(activity));
        this.contentLayout.setLayoutParams(contentLayoutParams);
        this.setContentView(this.contentLayout);
        // 初始化内容布局中的屏幕快照图片空间
        screenShotImageView = new ImageView(activity);
        this.contentLayout.addView(screenShotImageView);
        screenShotImageView.setX(0);
        screenShotImageView.setY(0);
        RelativeLayout.LayoutParams screenShotImageViewParams = new RelativeLayout.LayoutParams(screenWidth, screenHeight);
        screenShotImageView.setLayoutParams(screenShotImageViewParams);
        screenShotImageView.setBackgroundColor(Color.BLUE);
        // 显示popupwindow
        this.showAtLocation(activityRootView, Gravity.TOP, 0, 0);
        // 构建Bitmap对象
        Bitmap bitmap = Bitmap.createBitmap(SizeTool.getScreenWidth(activity), SizeTool.getScreenHeight(activity), Bitmap.Config.ARGB_8888);
        // 获取当前屏幕快照并设置到imageView控件中
        View decorview = activity.getWindow().getDecorView();
        decorview.setDrawingCacheEnabled(true);
        bitmap = decorview.getDrawingCache();
        screenShotImageView.setImageBitmap(bitmap);

        // 屏幕截图控件后移动画
        AnimationSet screenShotAnimationSet = new AnimationSet(true);
        AlphaAnimation screenShotAlphaAnimation = new AlphaAnimation(1.0f , 0.7f);
        ScaleAnimation screenShotScaleAnimation = new ScaleAnimation(1.0f, 0.8f, 1.0f, 0.8f , Animation.RELATIVE_TO_SELF , 0.5f , Animation.RELATIVE_TO_SELF , 0.5f);
        screenShotAnimationSet.addAnimation(screenShotAlphaAnimation);
        screenShotAnimationSet.addAnimation(screenShotScaleAnimation);
        screenShotAnimationSet.setDuration(500);
        screenShotAnimationSet.setFillAfter(true);
        screenShotImageView.startAnimation(screenShotAnimationSet);

        
    }

}
