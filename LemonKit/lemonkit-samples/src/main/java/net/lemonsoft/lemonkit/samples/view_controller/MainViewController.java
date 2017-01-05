package net.lemonsoft.lemonkit.samples.view_controller;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import net.lemonsoft.lemonkit.core_graphics.CGPoint;
import net.lemonsoft.lemonkit.core_graphics.CGRect;
import net.lemonsoft.lemonkit.core_graphics.CGSize;
import net.lemonsoft.lemonkit.core_native_view.LKScrollView;
import net.lemonsoft.lemonkit.ui_kit.UIColor;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.UIView;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.ui_control.UILabel;
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

        final LKScrollView scrollView = new LKScrollView(getApplicationContext());
        scrollView.setContentSize(CGSize.make(0, 3000));
//        scrollView.setBounces(false);
        scrollView.setLayoutParams(new RelativeLayout.LayoutParams(1060, 1700));
//        scrollView.setBackgroundColor(Color.GRAY);
        this.view.addView(scrollView);
        this.view.setBackgroundColor(UIColor.pinkColor());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.setContentOffset(CGPoint.make(400, 400), true);
            }
        }, 1000);
    }

}