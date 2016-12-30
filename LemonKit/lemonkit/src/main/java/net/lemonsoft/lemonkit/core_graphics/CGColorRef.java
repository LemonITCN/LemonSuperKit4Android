package net.lemonsoft.lemonkit.core_graphics;

/**
 * LemonKit CoreGraphics 颜色信息描述对象
 * Created by LiuRi on 2016/12/30.
 */

public class CGColorRef {

    private int colorValue;

    public int getColorValue() {
        return colorValue;
    }

    public CGColorRef(int colorValue) {
        this.colorValue = colorValue;
    }

}
