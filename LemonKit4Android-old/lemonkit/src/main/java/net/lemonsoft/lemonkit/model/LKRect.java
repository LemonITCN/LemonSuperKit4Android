package net.lemonsoft.lemonkit.model;

/**
 * 描述一个UI块的位置和宽高大小的model
 * Created by lemonsoft on 16-10-23.
 */
public class LKRect {

    public LKPoint origin;
    public LKSize size;

    public LKRect(double x, double y, double width, double height) {
        this.origin = new LKPoint(x, y);
        this.size = new LKSize(width, height);
    }

    public LKRect(LKPoint origin, LKSize size) {
        this.origin = origin;
        this.size = size;
    }
}
