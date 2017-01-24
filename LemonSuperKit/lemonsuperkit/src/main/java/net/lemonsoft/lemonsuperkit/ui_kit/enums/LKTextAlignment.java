package net.lemonsoft.lemonsuperkit.ui_kit.enums;

/**
 * LemonKit - 文本对齐方式
 * Created by LiuRi on 2017/1/4.
 */

public enum LKTextAlignment {

    LEFT(0),
    CENTER(1),
    RIGHT(2),
    JUSTIFIED(3);

    private int code;

    LKTextAlignment(int code) {
        this.code = code;
    }

}
