package net.lemonsoft.lemonkit.ui.view.baseView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.view.View;

/**
 * LKUIView，扩展View的属性
 * Created by LiuRi on 16/8/3.
 */
public class LKUIView extends View {

    /**
     * 边框线颜色
     */
    private int borderColor = Color.TRANSPARENT;
    /**
     * 边框线的宽度
     */
    private int borderWidth = 0;
    /**
     * 控件边框圆角的半径
     */
    private int cornerRadius = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        System.out.println("ON DRAW LA!!!!");
    }

    public LKUIView(Context context) {
        super(context);
    }

    public LKUIView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LKUIView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // setters and getters
    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        this.invalidate();
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        this.invalidate();
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        this.invalidate();
    }

}
