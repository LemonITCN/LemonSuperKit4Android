package net.lemonsoft.lemonkit.ui_kit;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;

import net.lemonsoft.lemonkit.core_graphics.CGColorRef;
import net.lemonsoft.lemonkit.core_native_tool.LKColorTool;

/**
 * LemonKit颜色描述对象
 * Created by LiuRi on 2016/12/29.
 */

public class UIColor {

    private Drawable drawable;

    public Drawable getDrawable() {
        return drawable;
    }

    public UIColor(float red, float green, float blue, float alpha) {
        this.drawable = new ColorDrawable(
                LKColorTool.getDefaultColorTool().colorWithARGBFloat(alpha, red, green, blue)
        );
    }

    public UIColor(String hexColor) {
        this.drawable = new ColorDrawable(Color.parseColor(hexColor));
    }

    /**
     * 获取CGColor颜色值描述对象
     *
     * @return CGColor颜色值描述对象
     */
    public CGColorRef cgColor() {
        int colorValue = Color.argb(0, 255, 255, 255);
        if (drawable instanceof ColorDrawable)
            colorValue = ((ColorDrawable) drawable).getColor();
        else if (drawable instanceof ShapeDrawable)
            colorValue = ((ShapeDrawable) drawable).getPaint().getColor();
        return new CGColorRef(colorValue);
    }

    /**
     * 根据红绿蓝
     *
     * @param red   红色颜色值
     * @param green 绿色颜色值
     * @param blue  蓝色颜色值
     * @param alpha 透明度颜色值
     * @return 生成的UIColor对象
     */
    public static UIColor colorWithRedGreenBlueAlpha(float red, float green, float blue, float alpha) {
        return new UIColor(alpha, red, green, blue);
    }

    /**
     * 绿色
     * #008000
     * 0,128,0
     */
    public static UIColor greenColor() {
        return new UIColor("#008000");
    }

    /**
     * 白色
     * #ffffff
     * 255,255,255
     */
    public static UIColor whiteColor() {
        return new UIColor("#ffffff");
    }

    /**
     * 透明颜色
     * #00ffffff
     * 0,255,255,255
     */
    public static UIColor clearColor() {
        return new UIColor(0, 255, 255, 255);
    }

    public Drawable getDRB() {
        return drawable;
    }

}
