package net.lemonsoft.lemonkit.util;

import android.graphics.Color;

/**
 * Created by lemonsoft on 16-10-23.
 */

public class ColorUtil {

    /**
     * 根据颜色的比例来生成颜色
     *
     * @param alpha 透明度
     * @param red   红色的比例
     * @param green 绿色的比例
     * @param blue  蓝色的比例
     * @return 生成的颜色值
     */
    public static int argbWithProportion(double alpha, double red, double green, double blue) {
        return Color.argb((int) (alpha * 255), (int) (red * 255), (int) (green * 255), (int) (blue * 255));
    }

}
