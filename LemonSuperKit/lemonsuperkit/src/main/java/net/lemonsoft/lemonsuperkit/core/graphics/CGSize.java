package net.lemonsoft.lemonsuperkit.core.graphics;

/**
 * Core Graphics 尺寸描述对象
 * Created by LiuRi on 2016/12/28.
 */

public class CGSize {

    /**
     * 描述一个尺寸的宽
     */
    public float width = 0f;
    /**
     * 描述一个尺寸的高
     */
    public float height = 0f;

    public CGSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public static CGSize make(float width, float height) {
        return new CGSize(width, height);
    }

}
