package net.lemonsoft.lemonsuperkit.native_ui.extend.adapter;

import net.lemonsoft.lemonsuperkit.core.graphics.CGPoint;
import net.lemonsoft.lemonsuperkit.native_ui.extend.delegate.LKScrollViewDelegate;
import net.lemonsoft.lemonsuperkit.native_ui.extend.view.LKScrollView;

/**
 * Created by LiuRi on 2017/1/9.
 */

public abstract class LKScrollViewDelegateAdapter implements LKScrollViewDelegate {

    @Override
    public void scrollViewDidScroll(LKScrollView scrollView) {

    }

    @Override
    public void scrollViewWillBeginDragging(LKScrollView scrollView) {

    }

    @Override
    public void scrollViewWillEndDragging(LKScrollView scrollView, CGPoint velocity, CGPoint targetContentOffset) {

    }

    @Override
    public void scrollViewDidEndDragging(LKScrollView scrollView, boolean decelerate) {

    }

    @Override
    public void scrollViewWillBeginDecelerating(LKScrollView scrollView) {

    }

    @Override
    public void scrollViewDidEndDecelerating(LKScrollView scrollView) {

    }

    @Override
    public void scrollViewDidEndScrollingAnimation(LKScrollView scrollView) {

    }

}
