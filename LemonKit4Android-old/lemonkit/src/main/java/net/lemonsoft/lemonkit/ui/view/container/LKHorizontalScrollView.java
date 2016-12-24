package net.lemonsoft.lemonkit.ui.view.container;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.HorizontalScrollView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 自定义水平滚动视图
 * Created by lemonsoft on 16-9-28.
 */
public class LKHorizontalScrollView extends HorizontalScrollView {

    private ScrollListener onScrollListener;

    public LKHorizontalScrollView(Context context) {
        super(context);
    }

    public LKHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LKHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (this.onScrollListener != null)
            onScrollListener.onScroll(l, t, oldl, oldt);
    }

    public void animateScrollTo(final int x, final int duration) {
        final Timer timer = new Timer("animateScroll");
        int interval = Math.abs(x - getScrollX()) / duration;
        if (x != getScrollX()) {
            final double sub = getScrollX() / (duration + 0.0);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (getScrollX() == x)
                        timer.cancel();
                    scrollTo((int) (getScrollX() + sub), getScrollY());
                }
            }, 0, 1);
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }

    public ScrollListener getOnScrollListener() {
        return onScrollListener;
    }

    public void setOnScrollListener(ScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public interface ScrollListener {
        void onScroll(int l, int t, int oldl, int oldt);

    }

}
