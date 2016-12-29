package net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view_controller;

import android.app.Activity;
import android.os.Bundle;

import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.UIView;

/**
 * 视图控制器
 * Created by LiuRi on 2016/12/28.
 */

public class UIViewController extends Activity {

    public UIView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.view = new UIView();
        setContentView(this.view);
        viewDidLoad();
    }


    @Override
    protected void onResume() {
        super.onResume();
        viewDidAppear();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewDidDisappear();
    }

    public void viewDidLoad() {

    }

    public void viewViewAppear() {

    }

    public void viewDidAppear() {

    }

    public void viewWillDisappear() {

    }

    public void viewDidDisappear() {

    }

}
