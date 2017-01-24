package net.lemonsoft.lemonsuperkit.ui_kit.adapter;

import android.view.View;

import net.lemonsoft.lemonsuperkit.ui_kit.delegate.UIScrollViewDelegate;
import net.lemonsoft.lemonsuperkit.core.graphics.CGPoint;
import net.lemonsoft.lemonsuperkit.ui_kit.ui_responder.ui_view.UIScrollView;

/**
 * UIScrollView的代理对象适配器
 * Created by LiuRi on 2017/1/9.
 */

public abstract class UIScrollViewDelegateAdapter implements UIScrollViewDelegate {

    @Override
    public void scrollViewDidScroll(UIScrollView scrollView) {

    }

    @Override
    public void scrollViewWillBeginDragging(UIScrollView scrollView) {

    }

    @Override
    public void scrollViewWillEndDragging(UIScrollView scrollView, CGPoint velocity, CGPoint targetContentOffset) {

    }

    @Override
    public void scrollViewDidEndDragging(UIScrollView scrollView, boolean decelerate) {

    }

    @Override
    public void scrollViewWillBeginDecelerating(UIScrollView scrollView) {

    }

    @Override
    public void scrollViewDidEndDecelerating(UIScrollView scrollView) {

    }

    @Override
    public void scrollViewDidEndScrollingAnimation(UIScrollView scrollView) {

    }

    @Override
    public void scrollViewDidEndZooming(UIScrollView scrollView, View view, float scale) {

    }

    @Override
    public boolean scrollViewShouldScrollToTop(UIScrollView scrollView) {
        return false;
    }

    @Override
    public void scrollViewDidScrollToTop(UIScrollView scrollView) {

    }
}
