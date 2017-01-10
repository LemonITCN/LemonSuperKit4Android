package net.lemonsoft.lemonkit.samples.view_controller;

import net.lemonsoft.lemonkit.ui_kit.adapter.UIScrollViewDelegateAdapter;
import net.lemonsoft.lemonkit.core.graphics.CGRect;
import net.lemonsoft.lemonkit.core.graphics.CGSize;
import net.lemonsoft.lemonkit.native_ui.tools.LKSizeTool;
import net.lemonsoft.lemonkit.ui_kit.UIColor;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.UIScrollView;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.UIView;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view_controller.UIViewController;

public class MainViewController extends UIViewController {

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();
//        UILabel label = new UILabel(CGRect.make(50, 150, 200, 100));
//        label.setText("Hello! LemonKit World.");
//        label.setBackgroundColor(UIColor.greenColor());
//        this.view.addSubView(label);
//        label.setClipsToBounds(true);
//        label.layer.setBorderWidth(1);
//        label.layer.setCornerRadius(80);
//        label.layer.setBorderColor(UIColor.redColor().cgColor());
//        this.view.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha(0.7f, 0.6f, 0.5f, 0.9f));
//
//        LemonBubble.showRight(this, "hello", 2000);

        this.view.setBackgroundColor(UIColor.pinkColor());

        UIScrollView scrollView = new UIScrollView(LKSizeTool.getDefaultSizeTool().screenFrame());
        scrollView.setContentSize(CGSize.make(0, 1000));
        this.view.addSubView(scrollView);
        UIView view = new UIView(CGRect.make(0, 0, 100, 100));
        view.setBackgroundColor(UIColor.hotpinkColor());
        scrollView.addSubView(view);
        scrollView.setDelegate(new UIScrollViewDelegateAdapter() {
            @Override
            public void scrollViewDidScroll(UIScrollView scrollView) {
                super.scrollViewDidScroll(scrollView);
                System.out.println(scrollView.getContentOffset().y);
            }
        });


//        final LKScrollView scrollView = new LKScrollView(getApplicationContext());
//        scrollView.setContentSize(CGSize.make(0, 3000));
////        scrollView.setBounces(false);
//        scrollView.setLayoutParams(new RelativeLayout.LayoutParams(1060, 1700));
////        scrollView.setBackgroundColor(Color.GRAY);
//        this.view.addView(scrollView);
//        scrollView.setDelegate(new LKScrollViewDelegateAdapter() {
//            @Override
//            public void scrollViewDidScroll(LKScrollView scrollView) {
//                super.scrollViewDidScroll(scrollView);
//                System.out.println(scrollView.getContentOffset().y);
//            }
//        });
    }


}