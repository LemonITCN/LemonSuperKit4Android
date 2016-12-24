package net.lemonsoft.lemonkit.ui.tip.LKBubble;

/**
 * 泡泡控件图文布局枚举
 * Created by lemonsoft on 16-10-23.
 */
public enum LKBubbleLayoutStyle {

    /**
     * 图上文下
     */
    BUBBLE_LAYOUT_STYLE_ICON_TOP_TITLE_BOTTOM(0),
    /**
     * 图下文上
     */
    BUBBLE_LAYOUT_STYLE_ICON_BOTTOM_TITLE_TOP(3),
    /**
     * 图左文右
     */
    BUBBLE_LAYOUT_STYLE_ICON_LEFT_TITLE_RIGHT(1),
    /**
     * 图右文左
     */
    BUBBLE_LAYOUT_STYLE_ICON_RIGHT_TITLE_LEFT(4),
    /**
     * 只显示图
     */
    BUBBLE_LAYOUT_STYLE_ICON_ONLY(2),
    /**
     * 只显示文
     */
    BUBBLE_LAYOUT_STYLE_TITLE_ONLY(5);

    private int code;

    public int getCode() {
        return code;
    }

    LKBubbleLayoutStyle(int code){
        this.code = code;
    }

}
