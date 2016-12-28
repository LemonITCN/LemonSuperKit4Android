package net.lemonsoft.lemonkit.ui_kit.ui_responder.ui_view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import net.lemonsoft.lemonkit.core_base.LemonKit;
import net.lemonsoft.lemonkit.core_graphics.CGRect;
import net.lemonsoft.lemonkit.core_tool.LKSizeTool;

/**
 * 视图控件，LemonKit中所有视图控件的父类
 * Created by LiuRi on 2016/12/28.
 */

public class UIView extends RelativeLayout {

    /**
     * LemonKit核心对象
     */
    protected LemonKit _LK = LemonKit.instance();
    /**
     * 尺寸控件类
     */
    protected LKSizeTool _ST = LKSizeTool.getPrivateSizeTool();
    /**
     * 控件的矩形信息
     */
    protected CGRect frame = new CGRect(0f, 0f, 0f, 0f);
    /**
     * 实际的内容控件
     */
    protected View _rView;

    /**
     * 父层控件
     */
    private UIView _superView;

    public UIView() {
        this(LemonKit.instance().getAppContext());
    }

    /**
     * 通过初始化frame来构造对象
     *
     * @param frame 控件外观矩形信息描述对象
     */
    public UIView(CGRect frame) {
        this();
        this.frame = frame;
    }

    /**
     * 子类通过此构造方法把基础的AndroidView控件封装成LemonKit的UIView子控件
     *
     * @param view androidView控件
     */
    protected UIView(View view) {
        this();
        this._rView = view;
        this.addView(view);
    }

    public UIView(Context context) {
        super(context);
    }

    /**
     * 获取当前视图的矩形信息
     *
     * @return 矩形信息
     */
    public CGRect getFrame() {
        return frame;
    }

    /**
     * 设置控件的X坐标，单位DP
     *
     * @param x 横坐标X，单位DP
     */
    @Override
    public void setX(float x) {
        super.setX(_ST.DP(x));
        this.frame.origin.x = x;
        refresh();
    }

    /**
     * 设置控件的Y坐标，单位DP
     *
     * @param y 纵坐标Y，单位DP
     */
    @Override
    public void setY(float y) {
        super.setY(_ST.DP(y));
        this.frame.origin.y = y;
        refresh();
    }

    /**
     * 设置控件的宽，单位DP
     *
     * @param width 控件的宽度，单位DP
     */
    public void setWidth(float width) {
        this.frame.size.width = width;
        this.setLayoutParams(new RelativeLayout.LayoutParams(_ST.DP(width), _ST.DP(this.frame.size.height)));
    }

    /**
     * 设置控件的高，单位DP
     *
     * @param height 控件的高度，单位DP
     */
    public void setHeight(float height) {
        this.frame.size.height = height;
        this.setLayoutParams(new RelativeLayout.LayoutParams(_ST.DP(this.frame.size.width), _ST.DP(height)));
    }

    /**
     * 设置控件的尺寸，单位DP
     *
     * @param width  控件的宽度，单位DP
     * @param height 控件的高度，单位DP
     */
    public void setSize(int width, int height) {
        this.setLayoutParams(new RelativeLayout.LayoutParams(_ST.DP(width), _ST.DP(height)));
    }

    /**
     * 设置布局参数，其中设置的宽高等信息单位为DP
     *
     * @param params 布局参数对象
     */
    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        this.frame.size.width = params.width;
        this.frame.size.height = params.height;
        params.width = _ST.DP(params.width);
        params.height = _ST.DP(params.height);
        super.setLayoutParams(params);
        refresh();
    }

    /**
     * 刷新布局
     */
    protected void refresh() {
        if (_rView != null) {
            _rView.setX(0);
            _rView.setY(0);
        }
    }

    // 无特殊要求的getter 、setter方法开始.....
    public UIView get_superView() {
        return _superView;
    }

    public void set_superView(UIView _superView) {
        this._superView = _superView;
    }
    // ......无特殊要求的getter 、setter方法结束
}
