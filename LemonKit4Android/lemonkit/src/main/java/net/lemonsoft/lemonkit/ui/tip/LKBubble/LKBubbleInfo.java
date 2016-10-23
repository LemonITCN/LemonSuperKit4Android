package net.lemonsoft.lemonkit.ui.tip.LKBubble;

import android.graphics.Bitmap;

import net.lemonsoft.lemonkit.model.LKPoint;
import net.lemonsoft.lemonkit.model.LKSize;

import java.util.List;

/**
 * LK泡泡控件的信息描述model对象
 * Created by lemonsoft on 16-10-23.
 */
public class LKBubbleInfo {

    /**
     * 泡泡控件的大小
     */
    public LKSize bubbleSize;
    /**
     * 泡泡控件的圆角半径
     */
    public double cornerRadius;
    /**
     * 图文布局属性
     */
    public LKBubbleLayoutStyle layoutStyle;
    /**
     * 图标动画
     */
    public LKBubbleCustomAnimationCallback iconAnimation;
    /**
     * 进度被改变的回调block
     */
    public LKBubbleOnProgressChangedCallback onProgressChanged;
    /**
     * 图标数组，如果该数组为空或者该对象为null，那么显示自定义动画，如果图标为一张，那么固定显示那个图标，大于一张的时候显示图片帧动画
     */
    public List<Bitmap> iconArray;
    /**
     * 要显示的标题
     */
    public String title;
    /**
     * 帧动画时间间隔
     */
    public double frameAnimationTime;
    /**
     * 图标占比 0 - 1，图标控件的边长占高度的比例
     */
    public double proportionOfIcon;
    /**
     * 间距占比 0 - 1，图标控件和标题控件之间距离占整个控件的比例（如果横向布局那么就相当于宽度，纵向布局相当于高度）
     */
    public double proportionOfSpace;
    /**
     * 内边距占比 0 - 1，整个泡泡控件的内边距，x最终为左右的内边距，y最终为上下的内边距（左右内边距以宽度算最终的像素值，上下边距以高度算最终的像素值）
     */
    public LKPoint proportionOfPadding;
    /**
     * 位置样式
     */
    public LKBubbleLocationStyle locationStyle;
    /**
     * 泡泡控件显示时偏移，当位置样式为上中的时候，偏移值是向下移动，当位置样式为底部时候，偏移值是向上移动
     */
    public double proportionOfDeviation;
    /**
     * 是否展示蒙版，展示蒙版后，显示泡泡控件时会产生一个蒙版层来拦截所有其他控件的点击事件
     */
    public boolean isShowMaskView;
    /**
     * 蒙版颜色
     */
    public int maskColor;
    /**
     * 背景色
     */
    public int backgroundColor;
    /**
     * 图标渲染色
     */
    public int iconColor;
    /**
     * 标题文字颜色
     */
    public int titleColor;
    /**
     * 标题字体大小
     */
    public double titleFontSize;
    /**
     * key，随机数，用于标志一个info的唯一性，关闭时候会通过这个验证
     */
    private double key;

    public double getKey() {
        return key;
    }
}
