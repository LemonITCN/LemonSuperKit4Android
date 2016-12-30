package net.lemonsoft.lemonkit.core_native_model;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

import net.lemonsoft.lemonkit.core_graphics.CGColorRef;
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
        Drawable[] drawables = new Drawable[2];
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[5]);
        return layerDrawable;
    }

}
