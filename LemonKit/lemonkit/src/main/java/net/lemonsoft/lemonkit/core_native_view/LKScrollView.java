package net.lemonsoft.lemonkit.core_native_view;

import android.content.Context;
import android.graphics.Color;
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

    /**
     * 如果某一轴的的尺寸为0，那么该方向轴无法回弹
     * 如，width为0，那么x轴的最左侧和最右侧两侧无拉动回弹效果
     */
    private CGSize contentSize = CGSize.make(0, 0);
    /**
     * 弹簧回弹效果
     */
    private boolean bounces = true;

    private RelativeLayout contentView;
    private VelocityTracker tracker;
    private ScrollRunnable scrollRunnable;

    public LKScrollView(Context context) {
        super(context);
        this.contentView = new RelativeLayout(context);
        this.contentView.setBackgroundColor(Color.BLUE);
        this.contentView.setLayoutParams(new FrameLayout.LayoutParams(3000, 3000));
        TextView textView = new TextView(context);
        textView.setText("HELLO LemonKit");
        textView.setLayoutParams(new RelativeLayout.LayoutParams(30000, 30000));
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
        fingerCount = event.getPointerCount();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (scrollRunnable != null) {
                    scrollRunnable.endScroll();
                    scrollRunnable = null;
                }
                tracker = VelocityTracker.obtain();
                if (tracker != null)
                    tracker.addMovement(event);
                startX = contentView.getX();
                startY = contentView.getY();
                moveX = event.getX();
                moveY = event.getY();
                fingerCount++;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (tracker != null)
                    tracker.addMovement(event);
                float currentX = startX + event.getX() - moveX;
                if (currentX < 0 && currentX > -getMaxX())// 在中间横轴显示区域滑动
                    contentView.setX(currentX);
                else if (getXCanBounces())
                    contentView.setX(currentX);
                float currentY = startY + event.getY() - moveY;
                if (currentY < 0 && currentY > -getMaxY())// 在中间纵轴显示区域滑动
                    contentView.setY(currentY);
                else if (getYCanBounces())// 边缘，如果有回弹效果
                    contentView.setY(currentY);
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
            case MotionEvent.ACTION_POINTER_UP: {
                startX = contentView.getX();
                startY = contentView.getY();
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

    /**
     * 获取实际应该滚动范围的宽
     *
     * @return 宽度的数值
     */
    private int getContentWidth() {
        return (int) Math.max(getMeasuredWidth(), contentSize.width);
    }

    /**
     * 获取实际应该滚动范围的高
     *
     * @return 高度的数值
     */
    private int getContentHeight() {
        return (int) Math.max(getMeasuredHeight(), contentSize.height);
    }

    /**
     * 获取最大的x坐标
     *
     * @return x坐标值
     */
    private int getMaxX() {
        return getContentWidth() - getMeasuredWidth();
    }

    /**
     * 获取最大的y坐标
     *
     * @return y坐标值
     */
    private int getMaxY() {
        return getContentHeight() - getMeasuredHeight();
    }

    /**
     * 判断当前水平方向是否能有弹簧回弹效果
     *
     * @return 是否有回弹效果的布尔值
     */
    private boolean getXCanBounces() {
        return contentSize.width != 0 && bounces;
    }

    /**
     * 判断当前垂直方向是否能有弹簧回弹效果
     *
     * @return 是否有回弹效果的布尔值
     */
    private boolean getYCanBounces() {
        return contentSize.height != 0 && bounces;
    }

    /**
     * 设置y坐标
     *
     * @param y 纵坐标
     */
    public void setY(float y) {
        if (y > 0 || y < -getMaxY()) {
            // 需要根据是否能回弹来处理
            if (y < 0)
                if (getYCanBounces()) contentView.setY(y);
                else contentView.setY(-getMaxY());
            else
                contentView.setY(getYCanBounces() ? y : 0);
        }

    }

    public CGSize getContentSize() {
        return contentSize;
    }

    public void setContentSize(CGSize contentSize) {
        this.contentSize = contentSize;
    }

    public boolean isBounces() {
        return bounces;
    }

    public void setBounces(boolean bounces) {
        this.bounces = bounces;
    }

    private class ScrollRunnable implements Runnable {

        private Scroller mScroller;


        @Override
        public void run() {
            if (mScroller.computeScrollOffset()) {
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
            mScroller.fling((int) contentView.getX(), (int) contentView.getY(), velocityX, velocityY, -getMaxX(), 1000, -getMaxY(), 1000);
        }

        public void endScroll() {
            mScroller.forceFinished(true);
        }

    }

}
