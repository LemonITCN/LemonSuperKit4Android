package net.lemonsoft.lemonkit.samples.activities;

import android.graphics.Color;

import net.lemonsoft.lemonkit.core_graphics.CGRect;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.ui_control.UIButton;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.UIView;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view_controller.UIViewController;

public class MainViewController extends UIViewController {

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();
        UIView view = new UIView();
        view.setFrame(CGRect.make(100, 100, 100, 100));
        this.view.addSubView(view);
        view.setBackgroundColor(Color.RED);

        UIButton button = new UIButton();
        button.setFrame(CGRect.make(25, 25, 50, 50));
        view.addSubView(button);
    }

}
