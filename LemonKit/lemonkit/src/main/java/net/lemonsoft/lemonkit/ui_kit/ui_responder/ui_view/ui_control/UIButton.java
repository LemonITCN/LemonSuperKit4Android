package net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.ui_control;

import android.widget.Button;

import net.lemonsoft.lemonkit.core.graphics.CGRect;

/**
 * 按钮控件
 * Created by LiuRi on 2016/12/28.
 */

public class UIButton extends UIControl<Button> {

    public UIButton(CGRect frame) {
        super(Button.class, frame);
    }

    public UIButton() {
        super(Button.class);
    }

}
