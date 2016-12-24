package net.lemonsoft.lemonkit.ui.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by lemonsoft on 16-9-22.
 */

public class LKRelativeLayout extends RelativeLayout {

    /**
     * 事件向父控件拦截属性
     */
    private boolean eventParentInterception = false;
    /**
     * 事件向子控件拦截属性
     */
    private boolean eventChildrenInterception = false;

    public LKRelativeLayout(Context context) {
        super(context);
    }

    public LKRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LKRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isEventParentInterception() {
        return eventParentInterception;
    }

    public void setEventParentInterception(boolean eventParentInterception) {
        this.eventParentInterception = eventParentInterception;
    }

    public boolean isEventChildrenInterception() {
        return eventChildrenInterception;
    }

    public void setEventChildrenInterception(boolean eventChildrenInterception) {
        this.eventChildrenInterception = eventChildrenInterception;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.eventChildrenInterception;// 根据设置属性返回值
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.eventParentInterception;// 根据设置属性返回值
    }
}
