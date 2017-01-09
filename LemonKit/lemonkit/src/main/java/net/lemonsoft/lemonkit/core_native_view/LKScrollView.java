package net.lemonsoft.lemonkit.core_native_view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import net.lemonsoft.lemonkit.core_graphics.CGPoint;
import net.lemonsoft.lemonkit.core_graphics.CGSize;
import net.lemonsoft.lemonkit.core_native_delegate.LKScrollViewDelegate;

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
    /**
     * LKScrollView的代理对象
     */
    private LKScrollViewDelegate delegate = null;

    private RelativeLayout contentView;
    private VelocityTracker tracker;
    private ScrollRunnable scrollRunnable;

    public LKScrollView(Context context) {
        super(context);
        this.setBackgroundColor(Color.WHITE);
        this.contentView = new RelativeLayout(context);
        this.contentView.setBackgroundColor(Color.BLUE);
        this.contentView.setLayoutParams(new FrameLayout.LayoutParams(3000, 3000));
        TextView textView = new TextView(context);
        textView.setText("HELLO LemonKit");
        textView.setLayoutParams(new RelativeLayout.LayoutParams(30000, 30000));
        textView.setTextSize(200);
        textView.setTextColor(Color.WHITE);
        setBasicX(0);
        setBasicY(0);
        contentView.addView(textView);
        this.addView(contentView);
    }

    private float startX = 0;
    private float startY = 0;
    private float moveX = 0;
    private float moveY = 0;
    private boolean isTouching = false;

    /**
     * 初始化触摸开始的横轴数值
     *
     * @param event 触摸事件对象
     */
    private void initTouchStartX(MotionEvent event) {
        startX = contentView.getX();
        moveX = event.getX();
    }

    /**
     * 初始化触摸开始的纵轴数值
     *
     * @param event 触摸事件对象
     */
    private void initTouchStartY(MotionEvent event) {
        startY = contentView.getY();
        moveY = event.getY();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                isTouching = true;
                if (delegate != null)// 调用开始滑动的代理函数
                    delegate.scrollViewWillBeginDragging(this);
                if (scrollRunnable != null) {
                    scrollRunnable.endScroll();
                    scrollRunnable = null;
                }
                tracker = VelocityTracker.obtain();
                if (tracker != null)
                    tracker.addMovement(event);
                initTouchStartX(event);
                initTouchStartY(event);
                break;
            }

            case MotionEvent.ACTION_POINTER_DOWN: {
                System.out.println("点击了一根手指");
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (tracker != null)
                    tracker.addMovement(event);
                float currentX = startX + event.getX() - moveX;
                if (currentX < 0 && currentX > -getMaxX())// 在中间横轴显示区域滑动
                    setBasicX(currentX);
                else if (getXCanBounces())// 水平边缘，如果有回弹效果
                    setDampedX(currentX);
                else initTouchStartX(event);// 防止横向无回弹时候仍然拉动的话再次回拉时候距离延迟
                float currentY = startY + event.getY() - moveY;
                if (currentY < 0 && currentY > -getMaxY())// 在中间纵轴显示区域滑动
                    setBasicY(currentY);
                else if (getYCanBounces())// 垂直边缘，如果有回弹效果
                    setDampedY(currentY);
                else initTouchStartY(event);// 防止纵向无回弹时候仍然拉动的话再次回拉时候距离延迟
//                System.out.println("current y :" + currentY);
                break;
            }
            case MotionEvent.ACTION_UP: {
                if (tracker != null) {
                    //将当前事件添加到速度检测器中
                    tracker.addMovement(event);
                    //计算当前的速度
                    tracker.computeCurrentVelocity(1000);
                    //得到当前x方向速度
                    final float vX = tracker.getXVelocity();
                    //得到当前y方向的速度
                    final float vY = tracker.getYVelocity();
                    scrollRunnable = new ScrollRunnable(getContext());
                    scrollRunnable.startScroll((int) vX, (int) vY);
                    // 执行惯性滑动runnable
                    post(scrollRunnable);
                    // 自动处理溢出边界
                    autoDealOutOfBounds();
                }
                isTouching = false;
                break;
            }
            case MotionEvent.ACTION_CANCEL:
                //置空速度检测器
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
     * 自动处理溢出边界的问题
     */
    private void autoDealOutOfBounds() {
        if (contentView.getX() > 0)
            scrollToX(0, true);
        if (contentView.getY() > 0)
            scrollToY(0, true);
        if (contentView.getX() < -getMaxX())
            scrollToX(-getMaxX(), true);
        if (contentView.getY() < -getMaxY())
            scrollToY(-getMaxY(), true);
    }

    /**
     * 动画滚动到指定的水平位置
     *
     * @param x 水平位置x坐标
     */
    public void scrollToX(float x, boolean animated) {
        final ValueAnimator animator = ValueAnimator.ofFloat(contentView.getX(), x);
        animator.setDuration(animated ? 200 : 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (!isTouching)// 有触摸的时候，不适用动画改变
                    setBasicX((Float) animation.getAnimatedValue());
            }
        });
        animator.start();
    }

    /**
     * 动画滚动到指定的纵坐标
     *
     * @param y 垂直位置y坐标
     */
    public void scrollToY(float y, boolean animated) {
        ValueAnimator animator = ValueAnimator.ofFloat(contentView.getY(), y);
        animator.setDuration(animated ? 200 : 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (!isTouching)// 有触摸的时候，不适用动画改变
                    setBasicY((Float) animation.getAnimatedValue());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                delegate.scrollViewDidEndScrollingAnimation(LKScrollView.this);
            }
        });
        animator.start();
    }

    /**
     * 滚动到指定的位置
     *
     * @param point 位置信息
     */
    public void scrollTo(CGPoint point, boolean animated) {
        scrollToX(-point.x, true);
        scrollToY(-point.y, true);
    }

    /**
     * 一般情况下改变水平坐标的时候触发
     */
    private synchronized void setBasicX(float x) {
        if (delegate != null)// 调用代理函数
            delegate.scrollViewDidScroll(this);
        contentView.setX(x);
    }

    /**
     * 一般情况下改变垂直坐标的时候触发
     */
    private synchronized void setBasicY(float y) {
        if (delegate != null)// 调用代理函数
            delegate.scrollViewDidScroll(this);
        contentView.setY(y);
    }

    /**
     * 设置经过阻尼函数处理过的x坐标
     *
     * @param x 横坐标
     */
    private void setDampedX(float x) {
        if (x > 0)
            setBasicX((float) Math.sqrt(x) * 10);
        else if (x < -getMaxY())
            setBasicX((float) -(Math.sqrt(Math.abs(x) - getMaxX())) * 10 - getMaxX());
    }

    /**
     * 设置经过阻尼函数处理过的y坐标
     *
     * @param y 纵坐标
     */
    private void setDampedY(float y) {
        if (y > 0)
//            setBasicY((float) Math.sqrt(y) * 10);
            setBasicY(y / 3.0f);
        else if (y < -getMaxY())
            setBasicY((float) -(Math.sqrt(Math.abs(y) - getMaxY())) * 10 - getMaxY());
    }

    public CGPoint getContentOffset() {
        return new CGPoint(-contentView.getX(), -contentView.getY());
    }

    /**
     * 设置内容偏移位置信息
     *
     * @param point    偏移到的点的位置
     * @param animated 是否使用动画
     */
    public void setContentOffset(CGPoint point, boolean animated) {
        scrollTo(point, animated);
    }

    /**
     * 无动画直接定位到指定偏移坐标
     *
     * @param point 要偏移到的点
     */
    public void setContentOffset(CGPoint point) {
        setContentOffset(point, false);
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

    public LKScrollViewDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(LKScrollViewDelegate delegate) {
        this.delegate = delegate;
    }

    private class ScrollRunnable implements Runnable {

        private Scroller mScroller;


        @Override
        public void run() {
            if (mScroller.computeScrollOffset()) {
                if (contentView.getX() < 0)
                    setBasicX(Math.min(0, mScroller.getCurrX()));
                if (contentView.getY() < 0)
                    setBasicY(Math.min(0, mScroller.getCurrY()));
                postDelayed(this, 16);
            } else if (delegate != null) {// 调用滑动停止的代理函数
                delegate.scrollViewDidEndDecelerating(LKScrollView.this);
            }
        }

        public ScrollRunnable(Context context) {
            mScroller = new Scroller(context);
        }

        public void startScroll(int velocityX, int velocityY) {
            mScroller.fling((int) contentView.getX(), (int) contentView.getY(), velocityX, velocityY, -getMaxX(), 0, -getMaxY(), 0);
            if (delegate != null && mScroller.computeScrollOffset())// 调用即将停止drag的代理函数
                delegate.scrollViewWillEndDragging(LKScrollView.this, CGPoint.make(velocityX, velocityY), CGPoint.make(-mScroller.getFinalX(), -mScroller.getFinalY()));
            if (delegate != null) { // 调用停止drag的代理函数
                delegate.scrollViewDidEndDragging(LKScrollView.this, velocityX != 0 || velocityY != 0);
                delegate.scrollViewWillBeginDecelerating(LKScrollView.this);
            }
        }

        public void endScroll() {
            mScroller.forceFinished(true);
        }

    }

}
