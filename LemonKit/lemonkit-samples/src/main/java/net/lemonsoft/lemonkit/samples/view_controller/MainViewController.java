package net.lemonsoft.lemonkit.samples.view_controller;

import net.lemonsoft.lemonkit.core_graphics.CGRect;
import net.lemonsoft.lemonkit.ui_kit.UIColor;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.ui_control.UILabel;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view_controller.UIViewController;

public class MainViewController extends UIViewController {

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();
        UILabel label = new UILabel(CGRect.make(50, 50, 200, 100));
        label.setText("Hello! LemonKit World.");
        label.setBackgroundColor(UIColor.greenColor());
        this.view.addSubView(label);
        label.layer.setCornerRadius(5);
    }

}