package net.lemonsoft.lemonkit.core_native_view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import net.lemonsoft.lemonkit.core_graphics.CGSize;

/**
 * LemonKit - 自定义高级ScrollView
 * 支持多向滑动，边缘拉抻回弹
 * Created by LiuRi on 2017/1/4.
 */

public class LKScrollView extends FrameLayout {

    private CGSize contentSize;

    private RelativeLayout contentView;
    private VelocityTracker tracker;
    private ScrollRunnable scrollRunnable;

    public LKScrollView(Context context) {
        super(context);
        this.contentView = new RelativeLayout(context);
        this.contentView.setBackgroundColor(Color.BLUE);
        this.contentView.setLayoutParams(new FrameLayout.LayoutParams(10000, 10000));
        TextView textView = new TextView(context);
        textView.setText("HELLO LemonKit");
        textView.setLayoutParams(new RelativeLayout.LayoutParams(10000, 10000));
        textView.setTextSize(200);
        textView.setTextColor(Color.WHITE);
        contentView.setX(0);
        contentView.setY(0);
        contentView.addView(textView);
        this.addView(contentView);
    }

    private float startX = 0;
    private float startY = 0;
    private float moveX = 0;
    private float moveY = 0;
    private int fingerCount = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (scrollRunnable != null) {
                    scrollRunnable.endScroll();
                    scrollRunnable = null;
                }
                tracker = VelocityTracker.obtain();
                if (tracker != null)
                    tracker.addMovement(event);
                if (fingerCount == 0) {
                    startX = contentView.getX();
                    startY = contentView.getY();
                    moveX = event.getX();
                    moveY = event.getY();
                }
                fingerCount++;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (tracker != null)
                    tracker.addMovement(event);
                contentView.setX(startX + event.getX() - moveX);
                setY(startY + event.getY() - moveY);
                break;
            }
            case MotionEvent.ACTION_UP: {
                if (tracker != null) {
                    //将当前事件添加到检测器中
                    tracker.addMovement(event);
                    //计算当前的速度
                    tracker.computeCurrentVelocity(1000);
                    //得到当前x方向速度
                    final float vX = tracker.getXVelocity();
                    //得到当前y方向的速度
                    final float vY = tracker.getYVelocity();
                    scrollRunnable = new ScrollRunnable(getContext());
                    scrollRunnable.startScroll((int) vX, (int) vY);
                    //执行run方法
                    post(scrollRunnable);
                }
                fingerCount--;
                break;
            }
            case MotionEvent.ACTION_CANCEL:
                //释放速度检测器
                if (tracker != null) {
                    tracker.recycle();
                    tracker = null;
                }
                break;
        }
        return true;
    }

    public void setY(float y) {
        if (y < 0)
            contentView.setY(y);
        else
            contentView.setY((float) (y - Math.pow(y * 0.1, 2)));
    }

    public CGSize getContentSize() {
        return contentSize;
    }

    public void setContentSize(CGSize contentSize) {
        this.contentSize = contentSize;
    }

    private class ScrollRunnable implements Runnable {

        private Scroller mScroller;


        @Override
        public void run() {
            if (mScroller.computeScrollOffset()) {
                System.out.println(mScroller.getCurrX() + " , " + mScroller.getCurrY());
                contentView.setX(mScroller.getCurrX());
                contentView.setY(mScroller.getCurrY());
                contentView.setX(Math.min(0, mScroller.getCurrX()));
                contentView.setY(Math.min(0, mScroller.getCurrY()));
                postDelayed(this, 16);
            }
        }

        public ScrollRunnable(Context context) {
            mScroller = new Scroller(context);
        }

        public void startScroll(int velocityX, int velocityY) {
            mScroller.fling((int) contentView.getX(), (int) contentView.getY(), velocityX, velocityY, -10000, 100, -10000, 100);
        }

        public void endScroll() {
            mScroller.forceFinished(true);
        }

    }

}
