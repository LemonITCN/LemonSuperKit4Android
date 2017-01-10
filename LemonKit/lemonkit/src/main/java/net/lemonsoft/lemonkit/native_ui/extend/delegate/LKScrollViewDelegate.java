package net.lemonsoft.lemonkit.native_ui.extend.delegate;

import net.lemonsoft.lemonkit.core.graphics.CGPoint;
import net.lemonsoft.lemonkit.native_ui.extend.view.LKScrollView;

/**
 * LKScrollView的代理函数
 * Created by LiuRi on 2017/1/5.
 */

public interface LKScrollViewDelegate {

    /**
     * 只要ScrollView的内容偏移被改变，就会被回调
     */
    void scrollViewDidScroll(LKScrollView scrollView);

    // TODO
    /**
     * 只要ScrollView在被缩放的时候就会被回调（iOS>=3.2时候可用）
     */
//    void scrollViewDidZoom(LKScrollView scrollView);

    /**
     * 手指触摸ScrollView将要滑动时候被回调
     */
    void scrollViewWillBeginDragging(LKScrollView scrollView);

    /**
     * 手指即将停止触摸的时候被回调
     *
     * @param velocity            当前scrollView滚动的速度
     * @param targetContentOffset 照此速度移动的话的最终点
     */
    void scrollViewWillEndDragging(LKScrollView scrollView, CGPoint velocity, CGPoint targetContentOffset);

    /**
     * 当手指离开ScrollView时回调该方法
     *
     * @param decelerate 是否继续移动，如果继续移动，那么为true
     */
    void scrollViewDidEndDragging(LKScrollView scrollView, boolean decelerate);

    /**
     * 当手指离开ScrollView，滚动开始减速的时候调用
     */
    void scrollViewWillBeginDecelerating(LKScrollView scrollView);

    /**
     * 当手指离开ScrollView，滚动减速到停止后调用
     */
    void scrollViewDidEndDecelerating(LKScrollView scrollView);

    /**
     * 当ScrollView执行完动画之后被调用，通常指的是执行下面两个函数后被调用
     * setContentOffset()
     * scrollRectToVisible()
     */
    void scrollViewDidEndScrollingAnimation(LKScrollView scrollView);

//    TODO

    /**
     * 设置要缩放的 scrollView 上面的哪一个子视图 , 只能是子视图 , 不能是ScrollView本身
     *
     * @return 要缩放的子视图
     */
//    View viewForZoomingInScrollView(LKScrollView scrollView);

//    TODO
    /**
     * 当开始缩放的时候被回调
     *
     * @param view 要缩放的子视图
     */
//    void scrollViewWillBeginZooming(LKScrollView scrollView, View view);

    // TODO
    /**
     * 当已经缩放的时候回调该方法，缩放在预设最小值和最大值中间的时候才可用（在回弹动画之后被调用）
     *
     * @param view  缩放的子视图
     * @param scale 缩放的比例
     */
//    void scrollViewDidEndZooming(LKScrollView scrollView, View view, float scale);

    // TODO

    /**
     * 当用户点击状态栏的时候，调用此方法
     * 当要滚到视图顶部的时候回调此函数询问用户是否能回到顶部，该方法当设置scrollsToTop=true的时候才会回调
     *
     * @return 是否允许回到顶部的布尔值
     */
//    boolean scrollViewShouldScrollToTop(LKScrollView scrollView);

    // TODO

    /**
     * 当已经滚动到顶部之后回调的函数（动画执行完毕）
     */
//    void scrollViewDidScrollToTop(LKScrollView scrollView);

}
