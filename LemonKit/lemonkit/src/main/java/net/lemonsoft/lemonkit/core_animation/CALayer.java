package net.lemonsoft.lemonkit.core_animation;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;

import net.lemonsoft.lemonkit.core.graphics.CGColorRef;
import net.lemonsoft.lemonkit.native_ui.model.LKViewAppearanceModel;
import net.lemonsoft.lemonkit.native_ui.tools.LKColorTool;
import net.lemonsoft.lemonkit.native_ui.tools.LKSizeTool;
import net.lemonsoft.lemonkit.ui_kit.UIColor;
import net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view.UIView;

/**
 * Core Animation layer
 * Created by LiuRi on 2016/12/30.
 */

public class CALayer {

    /**
     * 尺寸工具类
     */
    protected LKSizeTool _ST = LKSizeTool.getDefaultSizeTool();

    /**
     * 颜色工具类
     */
    protected LKColorTool _CT = LKColorTool.getDefaultColorTool();
    /**
     * 所归属的控件
     */
    private UIView _v;
    /**
     * 外观样式，例如圆角、边框等
     */
    private LKViewAppearanceModel _appearance;
    /**
     * 超过边界之外的内容是否隐藏
     */
    private boolean masksToBounds = false;

    public CALayer(UIView view) {
        _v = view;
        _appearance = new LKViewAppearanceModel();
    }

    public void setBackgroundColor(UIColor color) {
        _appearance.setBackgroundColor(color);
        applyAppearance();
    }

    public void onDraw(Canvas canvas) {
        if (this.masksToBounds) {
            // 需要剪切应显示视图的其余部分
            Path path = new Path();
            path.addRoundRect(
                    new RectF(0, 0, _ST.DP(
                            _v.getFrame().size.width),
                            _ST.DP(_v.getFrame().size.height)
                    ),
                    _ST.DP(_appearance.getCornerRadius()),
                    _ST.DP(_appearance.getCornerRadius()),
                    Path.Direction.CW
            );
            canvas.clipPath(path, Region.Op.REPLACE);
        }
    }

    /**
     * 设置控件的圆角半径
     *
     * @param radius 控件的圆角半径
     */
    public void setCornerRadius(float radius) {
        _appearance.setCornerRadius(radius);
        applyAppearance();// 应用外观
    }

    /**
     * 设置控件的边框线的宽度
     *
     * @param width 控件的圆角半径
     */
    public void setBorderWidth(float width) {
        _appearance.setBorderWidth(width);
        applyAppearance();// 应用外观
    }

    /**
     * 设置空间的边框线颜色
     *
     * @param color 控件的边框线的颜色
     */
    public void setBorderColor(CGColorRef color) {
        _appearance.setBorderColor(color);
        applyAppearance();
    }

    /**
     * 根据所有设置把设置的样式应用到控件之上
     */
    private void applyAppearance() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // 高版本SDK使用新的API
            _v.setBackground(_appearance.createDrawable());
        } else {
            _v.setBackgroundDrawable(_appearance.createDrawable());
        }
    }


    public boolean isMasksToBounds() {
        return masksToBounds;
    }

    public void setMasksToBounds(boolean masksToBounds) {
        this.masksToBounds = masksToBounds;
        _v.postInvalidate();
    }
}
