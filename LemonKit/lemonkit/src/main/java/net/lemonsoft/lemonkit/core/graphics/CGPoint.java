package net.lemonsoft.lemonkit.core.graphics;

/**
 * Core Graphics 坐标点描述类
 * Created by LiuRi on 2016/12/28.
 */

public class CGPoint {

    /**
     * 描述一个坐标点的横坐标x
     */
    public float x = 0f;
    /**
     * 描述一个坐标点的纵坐标y
     */
    public float y = 0f;

    public CGPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static CGPoint make(float x, float y) {
        return new CGPoint(x, y);
    }
}
