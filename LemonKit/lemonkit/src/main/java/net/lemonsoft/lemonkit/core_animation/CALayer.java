package net.lemonsoft.lemonkit.core_animation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;

import net.lemonsoft.lemonkit.core_graphics.CGColorRef;
import net.lemonsoft.lemonkit.core_tool.LKColorTool;
import net.lemonsoft.lemonkit.core_tool.LKSizeTool;
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



    public CALayer(UIView view) {
        _v = view;
    }

    public void setBackground(Drawable drb) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            _v.setBackground(drb);
//            if (_v.get_rView() != null)
//                _v.get_rView().setBackground(LKColorTool.getDefaultColorTool().);
//        } else {
//            _v.get_rView().setBackgroundDrawable(_CT.colorWithDrawable(drb));
//            if (_v.get_rView() != null)
//                _v.get_rView().setBackgroundDrawable(color.getDRB());
//        }
    }

    /**
     * 设置控件的圆角半径
     *
     * @param radius 控件的圆角半径
     */
    public void setCornerRadius(float radius) {
//        radius = _ST.DP(radius);
//        int borderWidth = 0;// 加边框后会出现空心圆角矩形的效果，所以设置为0
//        float[] outerRadius = new float[8];
//        float[] innerRadius = new float[8];
//        for (int i = 0; i < 8; i++) {
//            outerRadius[i] = radius + borderWidth;
//            innerRadius[i] = radius;
//        }
//        Shape shape =
//        ShapeDrawable shapeDrawable = // 创建图形drawable
//                new ShapeDrawable(
//                        // 创建圆角矩形
//                        new RoundRectShape(outerRadius,
//                                new RectF(borderWidth, borderWidth, borderWidth, borderWidth),
//                                innerRadius));
//        shapeDrawable.getPaint().setColor(_v.getBackgroundColor().cgColor().getColorValue());// 使用指定的颜色绘制，即背景颜色
//        Drawable drawable;
//        Canvas canvas;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            // 高版本SDK使用新的API
//            _v.setBackground(shapeDrawable);
//        } else {
//            _v.setBackgroundDrawable(shapeDrawable);
//        }
    }

}
