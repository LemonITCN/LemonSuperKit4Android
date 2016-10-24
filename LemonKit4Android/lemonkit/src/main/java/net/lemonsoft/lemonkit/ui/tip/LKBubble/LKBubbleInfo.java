package net.lemonsoft.lemonkit.ui.tip.LKBubble;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;

import net.lemonsoft.lemonkit.model.LKPoint;
import net.lemonsoft.lemonkit.model.LKRect;
import net.lemonsoft.lemonkit.model.LKSize;
import net.lemonsoft.lemonkit.util.ColorUtil;
import net.lemonsoft.lemonkit.util.SizeUtil;

import java.util.ArrayList;
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

    private Context context;

    public double getKey() {
        return key;
    }

    private int dp(float pxValue) {
        return SizeUtil.pxToDip(context, pxValue);
    }

    private LKSize screenSize() {
        return new LKSize(dp(SizeUtil.getScreenWidth(context)), dp(SizeUtil.getScreenHeight(context)));
    }

    public LKBubbleInfo(Context context) {
        this.bubbleSize = new LKSize(dp(180), dp(120));
        this.cornerRadius = dp(8);
        this.layoutStyle = LKBubbleLayoutStyle.BUBBLE_LAYOUT_STYLE_ICON_TOP_TITLE_BOTTOM;
        this.iconAnimation = null;
        this.onProgressChanged = null;
        this.iconArray = null;
        this.title = "LKBubble";
        this.frameAnimationTime = 100;
        this.proportionOfIcon = 0.675;
        this.proportionOfSpace = 0.1;
        this.proportionOfPadding = new LKPoint(0.1, 0.1);
        this.locationStyle = LKBubbleLocationStyle.BUBBLE_LOCATION_STYLE_CENTER;
        this.proportionOfDeviation = 0;
        this.isShowMaskView = true;
        this.maskColor = ColorUtil.argbWithProportion(0.2, 0.1, 0.1, 0.1);
        this.backgroundColor = ColorUtil.argbWithProportion(0.8, 0, 0, 0);
        this.iconColor = Color.WHITE;
        this.titleColor = Color.WHITE;
        this.titleFontSize = dp(13);

        // 生成随机的key
        this.key = Math.random();
    }

    public LKBubbleInfo(Context context, String title, Bitmap icon) {
        this(context);
        this.title = title;
        this.iconArray = new ArrayList<>();
        this.iconArray.add(icon);
    }

    /**
     * 计算泡泡控件的整体控件frame
     *
     * @return 整体控件的frame信息对象
     */
    public LKRect calBubbleViewFrame() {
        double y;
        switch (this.locationStyle) {
            case BUBBLE_LOCATION_STYLE_TOP:
                y = 0;
                break;
            case BUBBLE_LOCATION_STYLE_CENTER:
                y = (screenSize().height - bubbleSize.height) / 2;
                break;
            default:
                y = screenSize().height - this.bubbleSize.height;
                break;
        }
        y += (this.locationStyle != LKBubbleLocationStyle.BUBBLE_LOCATION_STYLE_BOTTOM ? 1 : -1) *
                (this.proportionOfDeviation * screenSize().height);
        return new LKRect((screenSize().width - this.bubbleSize.width) / 2, y,
                this.bubbleSize.width, this.bubbleSize.height);
    }

    /**
     * 计算泡泡控件中图标的控件frame
     *
     * @return 图标控件的frame信息对象
     */
    public LKRect calIconViewFrame() {
        LKSize bubbleContentSize = new LKSize(this.bubbleSize.width * (1 - this.proportionOfPadding.x * 2),
                this.bubbleSize.height * (1 - this.proportionOfPadding.y * 2));
        double baseX = this.bubbleSize.width * this.proportionOfPadding.x;
        double baseY = this.bubbleSize.height * this.proportionOfPadding.y;
        double iconWidth = this.layoutStyle == LKBubbleLayoutStyle.BUBBLE_LAYOUT_STYLE_ICON_TOP_TITLE_BOTTOM ||
                this.layoutStyle == LKBubbleLayoutStyle.BUBBLE_LAYOUT_STYLE_ICON_ONLY ||
                this.layoutStyle == LKBubbleLayoutStyle.BUBBLE_LAYOUT_STYLE_ICON_BOTTOM_TITLE_TOP ?
                bubbleContentSize.height * this.proportionOfIcon :
                bubbleContentSize.height * this.proportionOfIcon;
        LKRect iconFrame = new LKRect(baseX, baseY + (bubbleContentSize.height - iconWidth) / 2, iconWidth, iconWidth);
        switch (this.layoutStyle) {
            case BUBBLE_LAYOUT_STYLE_ICON_TOP_TITLE_BOTTOM:
                iconFrame.origin.x += (bubbleContentSize.width - iconWidth) / 2;
                iconFrame.origin.y = baseY;
                break;
            case BUBBLE_LAYOUT_STYLE_ICON_BOTTOM_TITLE_TOP:
                iconFrame.origin.x += (bubbleContentSize.width - iconWidth) / 2;
                iconFrame.origin.y = baseY + bubbleContentSize.height - iconWidth;
                break;
            case BUBBLE_LAYOUT_STYLE_ICON_RIGHT_TITLE_LEFT:
                iconFrame.origin.x += bubbleContentSize.width - iconWidth;
                break;
            case BUBBLE_LAYOUT_STYLE_TITLE_ONLY:
                iconFrame = new LKRect(0, 0, 0, 0);
                break;
            case BUBBLE_LAYOUT_STYLE_ICON_ONLY:
                iconFrame.origin.x += (bubbleContentSize.width - iconWidth) / 2;
                break;
            default:
                break;
        }
        return iconFrame;
    }

    /**
     * 计算泡泡控件中标题的frame
     *
     * @return 标题控件的frame信息对象
     */
    public LKRect calTitleViewFrame() {
        LKRect iconFrame = this.calIconViewFrame();
        LKSize bubbleContentSize = new LKSize(this.bubbleSize.width * (1 - this.proportionOfPadding.x * 2),
                this.bubbleSize.height * (1 - this.proportionOfPadding.y * 2));
        double baseX = this.bubbleSize.width * this.proportionOfPadding.x;
        double baseY = this.bubbleSize.height * this.proportionOfPadding.y;
        double titleWidth = this.layoutStyle == LKBubbleLayoutStyle.BUBBLE_LAYOUT_STYLE_ICON_TOP_TITLE_BOTTOM ||
                this.layoutStyle == LKBubbleLayoutStyle.BUBBLE_LAYOUT_STYLE_ICON_BOTTOM_TITLE_TOP ||
                this.layoutStyle == LKBubbleLayoutStyle.BUBBLE_LAYOUT_STYLE_TITLE_ONLY ?
                bubbleContentSize.width :
                bubbleContentSize.width * (1 - this.proportionOfSpace) - iconFrame.size.width;
        double titleHeight = titleFontSize;
        LKRect titleFrame = new LKRect(baseX, baseY + (bubbleContentSize.height - titleHeight) / 2,
                titleWidth, titleHeight);
        switch (this.layoutStyle) {
            case BUBBLE_LAYOUT_STYLE_ICON_TOP_TITLE_BOTTOM:
                titleFrame.origin.y = iconFrame.origin.y + iconFrame.size.height +
                        bubbleContentSize.height * this.proportionOfSpace;
                break;
            case BUBBLE_LAYOUT_STYLE_ICON_LEFT_TITLE_RIGHT:
                titleFrame.origin.x = iconFrame.origin.x + iconFrame.size.width + bubbleContentSize.width * this.proportionOfSpace;
                break;
            case BUBBLE_LAYOUT_STYLE_ICON_ONLY:
                titleFrame = new LKRect(0, 0, 0, 0);
                break;
            case BUBBLE_LAYOUT_STYLE_ICON_BOTTOM_TITLE_TOP:
                titleFrame.origin.y = baseY + (bubbleContentSize.height *
                        (1 - this.proportionOfIcon - this.proportionOfSpace) - titleHeight) / 2;
            default:
                break;
        }
        return titleFrame;
    }

}
