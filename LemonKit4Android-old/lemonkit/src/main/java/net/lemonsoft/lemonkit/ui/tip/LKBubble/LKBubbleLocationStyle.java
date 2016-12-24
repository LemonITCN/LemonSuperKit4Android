package net.lemonsoft.lemonkit.ui.tip.LKBubble;

/**
 * 泡泡控件位置枚举
 * Created by lemonsoft on 16-10-23.
 */
public enum LKBubbleLocationStyle {

    /**
     * 位于屏幕顶部
     */
    BUBBLE_LOCATION_STYLE_TOP(0),
    /**
     * 位于屏幕中间
     */
    BUBBLE_LOCATION_STYLE_CENTER(1),
    /**
     * 位于屏幕底部
     */
    BUBBLE_LOCATION_STYLE_BOTTOM(2);

    private int code;

    public int getCode() {
        return code;
    }

    LKBubbleLocationStyle(int code) {
        this.code = code;
    }
}
