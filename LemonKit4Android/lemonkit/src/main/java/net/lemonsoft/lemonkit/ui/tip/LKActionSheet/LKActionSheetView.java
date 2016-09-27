package net.lemonsoft.lemonkit.ui.tip.LKActionSheet;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.lemonsoft.lemonkit.ui.view.layout.LKRelativeLayout;
import net.lemonsoft.lemonkit.util.ActivityUtil;
import net.lemonsoft.lemonkit.util.SizeTool;

import java.util.ArrayList;
import java.util.List;

/**
 * ActionSheet视图
 * Created by 1em0nsOft on 2016/9/21.
 */
public class LKActionSheetView extends PopupWindow {

    private Activity inActivity;

    private RelativeLayout contentLayout;// 内容布局
    private ImageView screenShotImageView;// 背景图
    private LKRelativeLayout actionSheetLayout;// actionSheet主内容布局

    private List<List<LKActionItem>> actionItems;// 事件保存 二维list
    private RelativeLayout headView;// 头部控件，标题控件在此布局中，用户可以在其中添加自己的控件来自定义头部样式
    private RelativeLayout bodyView;// 正文控件，actionSheet的所有选项控件均在次布局中
    private TextView titleView;// 头部标题文字显示控件

    private int groupHeightSpace;// 事件组的高度间隙
    private int headViewHeight;// 顶部View的高度，可以通过设置高度为0来隐藏头部控件
    private static final double VIEW_MAX_HEIGHT_IN_SCREEN = 0.4;// 最高屏幕高占比

    private Integer screenWidth;// 屏幕的宽度
    private Integer screenHeight;// 屏幕的高度

    public int getGroupHeightSpace() {
        return groupHeightSpace;
    }

    public void setGroupHeightSpace(int groupHeightSpace) {
        this.groupHeightSpace = groupHeightSpace;
    }

    public int getHeadViewHeight() {
        return headViewHeight;
    }

    public void setHeadViewHeight(int headViewHeight) {
        this.headViewHeight = headViewHeight;
    }

    public LKActionSheetView(Activity inActivity) {
        super();
        this.inActivity = inActivity;

        this.groupHeightSpace = SizeTool.dipToPx(inActivity, 5);// 设置默认的组间的空隙

        this.clear();

        // 计算屏幕的宽度和高度
        this.screenWidth = SizeTool.getScreenWidth(inActivity);
        this.screenHeight = SizeTool.getScreenHeight(inActivity);
        View activityRootView = inActivity.findViewById(android.R.id.content);
        // 刷新绘制Activity
        activityRootView.invalidate();
        // 设置整个popupwindow为和屏幕一样宽高
        this.setWidth(this.screenWidth);
        this.setHeight(this.screenHeight);
        // 设置内容布局
        this.contentLayout = new RelativeLayout(inActivity);
        this.contentLayout.setBackgroundColor(Color.BLACK);
        // 设置内容布局的大小
        final RelativeLayout.LayoutParams contentLayoutParams = new RelativeLayout.LayoutParams(
                this.screenWidth, this.screenHeight);
        this.contentLayout.setLayoutParams(contentLayoutParams);
        this.setContentView(this.contentLayout);
        // 初始化内容布局中的屏幕快照图片空间
        screenShotImageView = new ImageView(inActivity);
        this.contentLayout.addView(screenShotImageView);
        screenShotImageView.setX(0);
        if (!ActivityUtil.isTranslucentStatusBar(inActivity))// 当前不是沉浸状态栏
            screenShotImageView.setY(-SizeTool.getStatusBarHeight(inActivity));// 设置y坐标为负状态栏高度，以防动画执行不自然
        RelativeLayout.LayoutParams screenShotImageViewParams = new RelativeLayout.LayoutParams(this.screenWidth, this.screenHeight);
        screenShotImageView.setLayoutParams(screenShotImageViewParams);
        screenShotImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });// 设置背景触摸时动画隐藏

        // 初始化actionSheet布局
        this.actionSheetLayout = new LKRelativeLayout(inActivity);
        this.actionSheetLayout.setEventParentInterception(true);
        this.actionSheetLayout.setClickable(true);
        this.contentLayout.addView(this.actionSheetLayout);
        this.actionSheetLayout.setBackgroundColor(Color.argb(255, 215, 215, 215));
        RelativeLayout.LayoutParams actionSheetLayoutParams = new RelativeLayout.LayoutParams(this.screenWidth, 0);
        this.actionSheetLayout.setLayoutParams(actionSheetLayoutParams);
        this.actionSheetLayout.setX(0);

        // 初始化头部布局控件和标题控件
        this.headView = new RelativeLayout(inActivity);
        this.headView.setBackgroundColor(Color.argb(255, 242, 242, 242));
        this.titleView = new TextView(inActivity);
        this.titleView.setTextSize(12);
        this.titleView.setX(0);
        this.titleView.setY(0);
        this.headView.setX(0);
        this.headView.setY(0);
        this.titleView.setGravity(Gravity.CENTER);

        // 初始化正文布局控件
        this.bodyView = new RelativeLayout(inActivity);
        this.bodyView.setBackgroundColor(Color.argb(255, 230, 230, 230));

        // 拼装控件
        this.headView.addView(this.titleView);
        this.actionSheetLayout.addView(this.headView);
        this.actionSheetLayout.addView(this.bodyView);
    }

    /**
     * 动画显示LKActionSheet
     */
    public void show() {
        initHeadView();// 初始化顶部控件
        initBodyView();// 初始化内容控件

        this.screenShotImageView.setClickable(false);// 开始播放动画之前关闭截图控件的点击事件
        View activityRootView = inActivity.findViewById(android.R.id.content);
        // 显示popupwindow
        this.showAtLocation(activityRootView, Gravity.TOP, 0, 0);
        // 构建Bitmap对象
        Bitmap bitmap;
        bitmap = Bitmap.createBitmap(this.screenWidth, this.screenHeight, Bitmap.Config.ARGB_8888);
        // 获取当前屏幕快照并设置到imageView控件中
        View decorview = inActivity.getWindow().getDecorView();
        decorview.setDrawingCacheEnabled(true);
        bitmap = decorview.getDrawingCache();
        screenShotImageView.setImageBitmap(bitmap);

        // 屏幕截图控件后移动画
        AnimationSet screenShotAnimationSet = new AnimationSet(true);
        AlphaAnimation screenShotAlphaAnimation = new AlphaAnimation(1.0f, 0.4f);
        ScaleAnimation screenShotScaleAnimation = new ScaleAnimation(1.0f, 0.8f, 1.0f, 0.8f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        screenShotAnimationSet.addAnimation(screenShotAlphaAnimation);
        screenShotAnimationSet.addAnimation(screenShotScaleAnimation);
        screenShotAnimationSet.setDuration(500);
        screenShotAnimationSet.setFillAfter(true);
        screenShotAnimationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                screenShotImageView.setClickable(true);// 视图动画展示完毕，允许点击关闭
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        screenShotImageView.startAnimation(screenShotAnimationSet);

        // 初始化actionSheet显示动画
//        this.actionSheetLayout.setY(this.screenHeight - (this.calViewHeight() + this.headViewHeight) - SizeTool.getStatusBarHeight(inActivity));
//        this.actionSheetLayout.setY(this.screenHeight - (this.calViewHeight() + this.headViewHeight));
        Integer y = y = this.screenHeight - (this.calViewHeight() + this.headViewHeight) - SizeTool.getStatusBarHeight(inActivity);
        if (ActivityUtil.isTranslucentStatusBar(inActivity))// 当前时沉浸状态栏
            y = this.screenHeight - (this.calViewHeight() + this.headViewHeight);
        RelativeLayout.LayoutParams actionSheetLayoutParams = new RelativeLayout.LayoutParams(screenWidth, this.calViewHeight() + this.headViewHeight);
        this.actionSheetLayout.setLayoutParams(actionSheetLayoutParams);
        // 动画显示
        ObjectAnimator.ofFloat(this.actionSheetLayout, "y", this.screenHeight, y).setDuration(500).start();
    }

    /**
     * 隐藏ActionSheet
     */
    public void hide() {
        this.screenShotImageView.setClickable(false);

        // 屏幕截图控件前移动画
        AnimationSet screenShotAnimationSet = new AnimationSet(true);
        AlphaAnimation screenShotAlphaAnimation = new AlphaAnimation(0.4f, 1f);
        ScaleAnimation screenShotScaleAnimation = new ScaleAnimation(0.8f, 1f, 0.8f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        screenShotAnimationSet.addAnimation(screenShotAlphaAnimation);
        screenShotAnimationSet.addAnimation(screenShotScaleAnimation);
        screenShotAnimationSet.setDuration(500);
        screenShotAnimationSet.setFillAfter(true);
        screenShotImageView.startAnimation(screenShotAnimationSet);

        // 添加actionSheet隐藏动画监听
        screenShotAnimationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dismiss();// 隐藏动画执行完毕后关闭popupwindow
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // 设置actionSheet的Y坐标为屏幕高，然后通过动画过渡
        this.actionSheetLayout.setY(this.screenHeight);
//        RelativeLayout.LayoutParams actionSheetLayoutParams = new RelativeLayout.LayoutParams(screenWidth, this.calViewHeight() + this.headViewHeight);
//        RelativeLayout.LayoutParams actionSheetLayoutParams = new RelativeLayout.LayoutParams(screenWidth, this.calViewHeight() + this.headViewHeight + SizeTool.getStatusBarHeight(inActivity));
        Integer y = this.screenHeight - (this.calViewHeight() + this.headViewHeight) - SizeTool.getStatusBarHeight(inActivity);
        if (ActivityUtil.isTranslucentStatusBar(inActivity))
            y = this.screenHeight - (this.calViewHeight() + this.headViewHeight);
//        this.actionSheetLayout.setLayoutParams(actionSheetLayoutParams);
        // actionSheet隐藏动画
        ObjectAnimator.ofFloat(this.actionSheetLayout, "y", y, this.screenHeight).setDuration(500).start();
    }

    /**
     * 添加一个事件组
     */
    public void addActionGroup() {
        this.actionItems.add(new ArrayList<LKActionItem>());
    }

    /**
     * 移除指定位置的事件组
     *
     * @param index 要移除的事件组的元素下标
     */
    public void removeActionGroupAtIndex(int index) {
        if (index >= 0 && index < this.actionItems.size())
            this.actionItems.remove(index);
    }

    /**
     * 清除所有元素和事件组，并重新生成一个新事件组
     */
    public void clear() {
        this.actionItems = new ArrayList<>();
        this.actionItems.add(new ArrayList<LKActionItem>());
    }

    /**
     * 在指定事件组中添加一个事件
     *
     * @param actionItem 要添加的事件
     * @param groupIndex 要添加的组的索引
     */
    public void addAction(LKActionItem actionItem, int groupIndex) {
        this.actionItems.get(groupIndex).add(actionItem);
    }

    /**
     * 在指定的事件组的指定位置插入事件
     *
     * @param actionItem 要插入的事件组
     * @param groupIndex 要添加在的事件组索引
     * @param location   将其插入到指定的位置
     */
    public void insertAction(LKActionItem actionItem, int groupIndex, int location) {
        this.actionItems.get(groupIndex).add(location < this.actionItems.get(groupIndex).size() ? location : this.actionItems.get(groupIndex).size(), actionItem);
    }

    /**
     * 移除指定时间组的指定位置的事件
     *
     * @param groupIndex 事件组的索引
     * @param location   要删除的事件的索引
     */
    public void removeActionAtIndex(int groupIndex, int location) {
        if (groupIndex < this.actionItems.size() && location < this.actionItems.get(groupIndex).size())
            this.actionItems.get(groupIndex).remove(location);
    }

    /**
     * 设置ActionSheet的标题
     *
     * @param title 标题文字
     */
    public void setTitle(String title) {
        this.titleView.setText(title);
    }

    /**
     * 计算当前正文内容应有的高度
     *
     * @return 计算后的高度值
     */
    private Integer calContentHeight() {
        int height = this.groupHeightSpace * (this.actionItems.size() - 1);
        for (List<LKActionItem> group : this.actionItems) {
            for (LKActionItem item : group) {
                height += item.getHeight();
            }
        }
        return height;
    }

    /**
     * 计算控件的应有高度
     *
     * @return 计算出的应有高度值
     */
    private Integer calViewHeight() {
        Integer contentHeight = this.calContentHeight();
        Integer maxHeight = (int) (VIEW_MAX_HEIGHT_IN_SCREEN * this.screenHeight);
        return contentHeight > maxHeight ? maxHeight : contentHeight;
    }

    /**
     * 初始化头部控件
     */
    private void initHeadView() {
        RelativeLayout.LayoutParams headViewParams
                = new RelativeLayout.LayoutParams(this.screenWidth, this.headViewHeight - 2);
        this.headView.setLayoutParams(headViewParams);
        this.titleView.setLayoutParams(headViewParams);
    }

    /**
     * 初始化正文控件
     */
    private void initBodyView() {
        this.bodyView.setX(0);
        this.bodyView.setY(this.headViewHeight);
        RelativeLayout.LayoutParams bodyViewParams = new RelativeLayout.LayoutParams(this.screenWidth, calViewHeight());
        this.bodyView.setLayoutParams(bodyViewParams);
        Integer pointer = this.calContentHeight();// Y坐标指针，动态指向下一个控件的Y坐标位置
        this.bodyView.removeAllViews();
        for (List<LKActionItem> group : this.actionItems) {
            for (final LKActionItem item : group) {
                pointer -= item.getHeight();
                // 循环创建行布局
                RelativeLayout lineLayout = new RelativeLayout(inActivity);
                lineLayout.setBackgroundColor(Color.argb(255, 237, 237, 237));
                lineLayout.setX(0);
                lineLayout.setY(pointer);
                RelativeLayout.LayoutParams lineLayoutParams = new RelativeLayout.LayoutParams(this.screenWidth, item.getHeight() - 2);
                lineLayout.setLayoutParams(lineLayoutParams);
                lineLayout.addView(item.getContentView());
                lineLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.getAction() != null)// 调用触摸事件
                            item.getAction().onItemTouchAction();
                    }
                });
                this.bodyView.addView(lineLayout);
            }
            pointer -= this.groupHeightSpace;// Y指针减去组孔隙的高度
        }
    }

}
