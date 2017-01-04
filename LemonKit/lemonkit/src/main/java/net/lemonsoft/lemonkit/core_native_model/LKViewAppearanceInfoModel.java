package net.lemonsoft.lemonkit.core_native_model;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;

import net.lemonsoft.lemonkit.R;
import net.lemonsoft.lemonkit.core_graphics.CGColorRef;
import net.lemonsoft.lemonkit.core_graphics.CGRect;
import net.lemonsoft.lemonkit.core_native_tool.LKSizeTool;
import net.lemonsoft.lemonkit.core_native_ui_extension.drawable.RoundCornerImageDrawable;
import net.lemonsoft.lemonkit.ui_kit.UIColor;

/**
 * LemonKit 视图外观信息模型类
 * Created by LiuRi on 2016/12/30.
 */

public class LKViewAppearanceInfoModel {

    /**
     * 背景颜色
     */
    private UIColor backgroundColor = UIColor.clearColor();
    /**
     * 边框颜色
     */
    private CGColorRef borderColor = new CGColorRef(Color.argb(0, 255, 255, 255));
    /**
     * 边框宽度
     */
    private float borderWidth = 0f;
    /**
     * 圆角半径
     */
    private float cornerRadius = 0f;
    /**
     * 阴影颜色
     */
    private CGColorRef shadowColor = new CGColorRef(Color.argb(0, 0, 0, 0));
    /**
     * 阴影半径
     */
    private float shadowRadius = 0f;
    /**
     * 阴影透明度0-1
     */
    private float shadowOpacity = 1f;
    /**
     * 控件的矩形外观
     */
    private CGRect frame = CGRect.make(0, 0, 0, 0);

    /**
     * 尺寸控件类
     */
    protected final LKSizeTool _ST = LKSizeTool.getDefaultSizeTool();

    public LKViewAppearanceInfoModel() {

    }

    private Context context;

    /**
     * 通过context构造外观描述对象
     *
     * @param context 上下文对象
     */
    public LKViewAppearanceInfoModel(Context context) {
        this.context = context;
    }

    public UIColor getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(UIColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public CGColorRef getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(CGColorRef borderColor) {
        this.borderColor = borderColor;
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public CGColorRef getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(CGColorRef shadowColor) {
        this.shadowColor = shadowColor;
    }

    public float getShadowRadius() {
        return shadowRadius;
    }

    public void setShadowRadius(float shadowRadius) {
        this.shadowRadius = shadowRadius;
    }

    public float getShadowOpacity() {
        return shadowOpacity;
    }

    public void setShadowOpacity(float shadowOpacity) {
        this.shadowOpacity = shadowOpacity;
    }

    public Drawable createDrawable() {
        Drawable[] drawables = new Drawable[1];
        if (backgroundColor.getDrawable() instanceof ColorDrawable) {
            // 纯背景颜色，UIColor是通过颜色创建的（后期支持通过图片创建背景颜色）
            int realRadius = _ST.DP(this.cornerRadius);
            int borderWidth = _ST.DP(this.borderWidth);// 加边框后会出现空心圆角矩形的效果，所以设置为0
            float[] outerRadius = new float[8];
            float[] innerRadius = new float[8];
            for (int i = 0; i < 8; i++) {
                outerRadius[i] = realRadius + borderWidth;
                innerRadius[i] = realRadius;
            }

            GradientDrawable backDrawable = new GradientDrawable();
            backDrawable.setShape(GradientDrawable.RECTANGLE);// 圆角矩形
            backDrawable.setCornerRadius(_ST.DP(this.cornerRadius));// 圆角半径设置
            backDrawable.setColor(((ColorDrawable) backgroundColor.getDrawable()).getColor());// 背景颜色
            backDrawable.setStroke(_ST.DP(borderWidth), borderColor.getColorValue());// 设置边框宽度和颜色

            drawables[0] = backDrawable;
        }
        LayerDrawable layerDrawable = new LayerDrawable(drawables);
        return layerDrawable;
    }

}
