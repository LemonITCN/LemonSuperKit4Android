package net.lemonsoft.lemonkit.core_tool;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;

/**
 * LemonKit颜色工具类
 * Created by LiuRi on 2016/12/30.
 */

public class LKColorTool {

    private static LKColorTool _defaultColorTool;

    public static synchronized LKColorTool getDefaultColorTool() {
        if (_defaultColorTool == null)
            _defaultColorTool = new LKColorTool();
        return _defaultColorTool;
    }

    /**
     * 通过设置RGBA每种颜色的比例来生成颜色，每种值范围为0-1之间的浮点数
     *
     * @param alpha 透明度数值
     * @param red   红色数值
     * @param green 绿色数值
     * @param blue  蓝色数值
     * @return 颜色描述信息
     */
    public int colorWithARGBFloat(float alpha, float red, float green, float blue) {
        return Color.argb(
                (int) (Math.min(alpha, 1) * 255),
                (int) (Math.min(red, 1) * 255),
                (int) (Math.min(green, 1) * 255),
                (int) (Math.min(blue, 1) * 255)
        );
    }

    /**
     * 获取Drawable的颜色
     *
     * @param drawable drawable对象
     * @return 颜色值
     */
    public int colorWithDrawable(Drawable drawable) {
        int colorValue = Color.argb(0, 255, 255, 255);
        if (drawable instanceof ShapeDrawable)
            colorValue = ((ShapeDrawable) drawable).getPaint().getColor();
        else if (drawable instanceof ColorDrawable)
            colorValue = ((ColorDrawable) drawable).getColor();
        return colorValue;
    }

}
