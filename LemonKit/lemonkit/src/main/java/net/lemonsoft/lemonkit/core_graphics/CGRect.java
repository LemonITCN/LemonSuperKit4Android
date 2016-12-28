package net.lemonsoft.lemonkit.core_graphics;

/**
 * Core Graphics 矩形信息描述类
 * Created by LiuRi on 2016/12/28.
 */

public class CGRect {

    /**
     * 矩形的起点，即左上角的坐标点
     */
    public CGPoint origin;
    /**
     * 矩形的尺寸
     */
    public CGSize size;

    public CGRect(CGPoint origin, CGSize size) {
        this.origin = origin;
        this.size = size;
    }

    public CGRect(float x, float y, float width, float height) {
        this.origin = new CGPoint(x, y);
        this.size = new CGSize(width, height);
    }

    public static CGRect make(CGPoint origin, CGSize size) {
        return new CGRect(origin, size);
    }

    public static CGRect make(float x, float y, float width, float height) {
        return new CGRect(x, y, width, height);
    }
}
