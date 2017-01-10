package net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view;

import android.widget.RelativeLayout;

import net.lemonsoft.lemonkit.ui_kit.delegate.UIScrollViewDelegate;
import net.lemonsoft.lemonkit.core.graphics.CGPoint;
import net.lemonsoft.lemonkit.core.graphics.CGRect;
import net.lemonsoft.lemonkit.core.graphics.CGSize;
import net.lemonsoft.lemonkit.native_ui.extend.delegate.LKScrollViewDelegate;
import net.lemonsoft.lemonkit.native_ui.extend.view.LKScrollView;

/**
 * UIScrollView 对 LKScrollView的封装
 * 让其和iOS的使用更相近，所有的单位都换成DP
 * Created by LiuRi on 2017/1/5.
 */

public class UIScrollView extends UIView<LKScrollView> implements LKScrollViewDelegate {

    private UIScrollViewDelegate delegate;

    public UIScrollView() {
        super(LKScrollView.class);
    }

    public UIScrollView(CGRect frame) {
        super(LKScrollView.class, frame);
    }

    /**
     * 动画滚动到指定的水平位置
     *
     * @param x 水平位置x坐标
     */
    public void scrollToX(float x, boolean animated) {
        _gRV().scrollToX(_ST.DP(x), animated);
    }

    /**
     * 动画滚动到指定的纵坐标
     *
     * @param y 垂直位置y坐标
     */
    public void scrollToY(float y, boolean animated) {
        _gRV().scrollToY(_ST.DP(y), animated);
    }

    /**
     * 滚动到指定的位置
     *
     * @param point 位置信息
     */
    public void scrollTo(CGPoint point, boolean animated) {
        point.x = _ST.DP(point.x);
        point.y = _ST.DP(point.y);
        _gRV().scrollTo(point, animated);
    }

    public CGPoint getContentOffset() {
        return new CGPoint(_ST.pxToDp(_gRV().getContentOffset().x),
                _ST.pxToDp(_gRV().getContentOffset().y));
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
        return CGSize.make(_ST.pxToDp(_gRV().getContentSize().width), _ST.pxToDp(_gRV().getContentSize().height));
    }

    public void setContentSize(CGSize contentSize) {
        contentSize.width = _ST.DP(contentSize.width);
        contentSize.height = _ST.DP(contentSize.height);
        _gRV().setContentSize(contentSize);
    }

    public boolean isBounces() {
        return _gRV().isBounces();
    }

    public void setBounces(boolean bounces) {
        _gRV().setBounces(bounces);
    }

    public UIScrollViewDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(UIScrollViewDelegate delegate) {
        this.delegate = delegate;
        _gRV().setDelegate(this);
    }

    public void addSubView(UIView view) {
        _gRV().addView(view);
        view.setLayoutParams(new RelativeLayout.LayoutParams((int) view.frame.size.width, (int) view.frame.size.height));
    }

    // 中转调用LKScrollViewDelegate


    @Override
    public void scrollViewDidScroll(LKScrollView scrollView) {
        if (delegate != null)
            delegate.scrollViewDidScroll(this);
    }

    @Override
    public void scrollViewWillBeginDragging(LKScrollView scrollView) {
        if (delegate != null)
            delegate.scrollViewWillBeginDecelerating(this);
    }

    @Override
    public void scrollViewWillEndDragging(LKScrollView scrollView, CGPoint velocity, CGPoint targetContentOffset) {
        if (delegate != null)
            delegate.scrollViewWillEndDragging(this,
                    new CGPoint(_ST.pxToDp(velocity.x), _ST.pxToDp(velocity.y)),
                    new CGPoint(_ST.pxToDp(targetContentOffset.x), _ST.pxToDp(targetContentOffset.y)));
    }

    @Override
    public void scrollViewDidEndDragging(LKScrollView scrollView, boolean decelerate) {
        if (delegate != null)
            delegate.scrollViewDidEndDragging(this, decelerate);
    }

    @Override
    public void scrollViewWillBeginDecelerating(LKScrollView scrollView) {
        if (delegate != null)
            delegate.scrollViewWillBeginDecelerating(this);
    }

    @Override
    public void scrollViewDidEndDecelerating(LKScrollView scrollView) {
        if (delegate != null)
            delegate.scrollViewDidEndDecelerating(this);
    }

    @Override
    public void scrollViewDidEndScrollingAnimation(LKScrollView scrollView) {
        if (delegate != null)
            delegate.scrollViewDidEndScrollingAnimation(this);
    }
}
