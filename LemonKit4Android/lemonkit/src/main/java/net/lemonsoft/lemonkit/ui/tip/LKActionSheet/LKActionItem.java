package net.lemonsoft.lemonkit.ui.tip.LKActionSheet;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.lemonsoft.lemonkit.util.SizeUtil;

/**
 * LKActionSheet的action单元
 * Created by lemonsoft on 16-9-21.
 */
public class LKActionItem {

    /**
     * 子项触摸代理
     */
    public interface OnItemTouchDelegate {
        void onItemTouchAction();
    }

    private static int DEFAULT_HEIGHT;// 默认的标题行高

    private Context context;
    private View contentView;
    private OnItemTouchDelegate action;
    private Integer height;

    public LKActionItem(Context context, String title, Integer height, int textColor) {
        DEFAULT_HEIGHT = SizeUtil.dipToPx(context, 46);

        this.context = context;
        this.height = height == 0 ? DEFAULT_HEIGHT : height;

        Integer screenWidth = SizeUtil.getScreenWidth(context);

        TextView titleView = new TextView(context);
        titleView.setBackgroundColor(Color.TRANSPARENT);// 设置按钮透明背景色
        titleView.setText(title);
        titleView.setTextColor(textColor);
        titleView.setX(0);
        titleView.setY(0);
        titleView.setTextSize(15);// 设置默认字号
        RelativeLayout.LayoutParams titleViewParams = new RelativeLayout.LayoutParams(screenWidth, height - 1);
        titleView.setLayoutParams(titleViewParams);
        titleView.setGravity(Gravity.CENTER);// 对齐方式居中

        this.contentView = titleView;
    }

    public LKActionItem(Context context, String title, Integer height, int textColor, OnItemTouchDelegate action) {
        this(context, title, height, textColor);
        this.action = action;
    }

    public LKActionItem(Context context, String title, Integer height) {
        this(context, title, height, Color.BLACK);
    }

    public LKActionItem(Context context, Integer textColor, String title) {
        this(context, title, DEFAULT_HEIGHT, textColor);
    }

    public LKActionItem(Context context, String title) {
        this(context, title, DEFAULT_HEIGHT, Color.BLACK);
    }

    public LKActionItem(Context context, String title, Integer textColor, OnItemTouchDelegate action) {
        this(context, title, DEFAULT_HEIGHT, textColor);
        this.action = action;
    }

    public LKActionItem(Context context, String title, OnItemTouchDelegate action) {
        this(context, title, DEFAULT_HEIGHT, Color.BLACK);
        this.action = action;
    }

    public LKActionItem(Context context, View customView, Integer height) {
        this.context = context;
        this.contentView = customView;
        this.height = height;
    }

    public LKActionItem(Context context, View customView, Integer height, OnItemTouchDelegate action) {
        this.context = context;
        this.contentView = customView;
        this.height = height;
        this.action = action;
    }

    public View getContentView() {
        return contentView;
    }

    public void setContentView(View contentView) {
        this.contentView = contentView;
    }

    public OnItemTouchDelegate getAction() {
        return action;
    }

    public void setAction(OnItemTouchDelegate action) {
        this.action = action;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
