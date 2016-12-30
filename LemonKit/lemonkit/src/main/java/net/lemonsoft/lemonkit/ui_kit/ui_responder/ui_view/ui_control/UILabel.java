package net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.ui_control;

import android.widget.TextView;

import net.lemonsoft.lemonkit.core_graphics.CGRect;
import net.lemonsoft.lemonkit.ui_kit.UIFont;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.UIView;

/**
 * LemonKit 标签控件
 * Created by lemonsoft on 2016/12/29.
 */

public class UILabel extends UIView<TextView> {

    /**
     * 当前标签显示的字体
     */
    private UIFont font;

    public UILabel() {
        super(TextView.class);
    }

    public UILabel(CGRect frame) {
        super(TextView.class, frame);
    }

    public void setFont(UIFont font) {
        this.font = font;
    }

    public UIFont getFont() {
        return font;
    }

    public void setText(String text) {
        _gRV().setText(text);
    }

    public String getText() {
        return _gRV().getText().toString();
    }

}
