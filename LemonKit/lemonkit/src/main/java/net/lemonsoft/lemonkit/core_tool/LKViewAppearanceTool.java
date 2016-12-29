package net.lemonsoft.lemonkit.core_tool;

import android.view.View;
import android.view.ViewGroup;

import net.lemonsoft.lemonkit.core_graphics.CGPoint;
import net.lemonsoft.lemonkit.core_graphics.CGRect;
import net.lemonsoft.lemonkit.core_graphics.CGSize;

import java.lang.reflect.Constructor;

/**
 * LemonKit 原生视图外观修改工具类
 * 本类中所有参数中设计的尺寸的单位都为DP
 * Created by LiuRi on 2016/12/28.
 */

public class LKViewAppearanceTool {

    /**
     * 尺寸工具
     */
    private LKSizeTool _ST = LKSizeTool.getDefaultSizeTool();

    /**
     * 单利对象存储变量
     */
    private static LKViewAppearanceTool _viewAppearanceTool;

    /**
     * 单例方法
     *
     * @return 工具类的单例对象
     */
    public static LKViewAppearanceTool getDefaultViewAppearanceTool() {
        if (_viewAppearanceTool == null)
            _viewAppearanceTool = new LKViewAppearanceTool();
        return _viewAppearanceTool;
    }

    /**
     * 设置控件的X坐标，支持浮点数，单位DP
     *
     * @param view 要设置横坐标的控件
     * @param x    横坐标数值，单位DP
     */
    public void setX(View view, float x) {
        view.setX(_ST.DP(x));
    }

    /**
     * 设置控件的Y坐标，支持浮点数，单位DP
     *
     * @param view 要设置纵坐标的控件
     * @param y    纵坐标数值，单位DP
     */
    public void setY(View view, float y) {
        view.setY(_ST.dpToPx(y));
    }

    /**
     * 设置控件起始位置，即控件的左上角的点的位置，单位DP
     *
     * @param view 要设置起始位置的控件
     * @param x    控件的X坐标
     * @param y    控件的Y坐标
     */
    public void setOrigin(View view, float x, float y) {
        setX(view, x);
        setY(view, y);
    }

    /**
     * 设置控件起始位置，即控件的左上角的点的位置，单位DP
     *
     * @param view   要设置起始位置的控件
     * @param origin 控件左上角点的描述对象
     */
    public void setOrigin(View view, CGPoint origin) {
        setX(view, origin.x);
        setY(view, origin.y);
    }

    /**
     * 根据控件的父容器类型自动创建布局参数的实例对象
     *
     * @param view 要创建布局参数的实例对象的控件
     * @return 布局参数的实例对象
     * @throws Exception 创建时候产生的异常
     */
    public ViewGroup.LayoutParams autoCreateLayoutParams(View view, int width, int height) {
        try {
            String containerClassName = view.getParent().getClass().getName();
            if (containerClassName == null || containerClassName.equals(""))
                throw new Exception("无法找到这个控件的容器");
            Class clazz = Class.forName(containerClassName + "$LayoutParams");
            Constructor<ViewGroup.LayoutParams> constructor = clazz.getConstructor(int.class, int.class);
            return constructor.newInstance(width, height);
        } catch (Exception e) {
            System.err.print("控件无父容器，设置布局参数对象为ViewGroup.LayoutParams");
            return new ViewGroup.LayoutParams(width, height);
        }
    }

    /**
     * 设置指定控件的宽度
     *
     * @param view  要设置宽度的控件
     * @param width 控件的宽度要设置的值
     */
    public void setWidth(View view, float width) {
        if (view.getLayoutParams() == null)
            view.setLayoutParams(autoCreateLayoutParams(view, _ST.DP(width), 0));
        view.getLayoutParams().width = _ST.DP(width);
    }

    /**
     * 设置制定控件的高度
     *
     * @param view   要设置高度的控件
     * @param height 控件的高度要设置的值
     */
    public void setHeight(View view, float height) {
        if (view.getLayoutParams() == null)
            view.setLayoutParams(autoCreateLayoutParams(view, 0, _ST.DP(height)));
        view.getLayoutParams().height = _ST.DP(height);
    }

    /**
     * 设置控件的尺寸，单位DP
     *
     * @param view   要设置尺寸的控件
     * @param width  控件的宽度，单位DP
     * @param height 控件的高度，单位DP
     */
    public void setSize(View view, float width, float height) {
        setWidth(view, width);
        setHeight(view, height);
    }

    /**
     * 设置控件的尺寸，使用尺寸描述对象
     *
     * @param view 要设置尺寸的控件
     * @param size 控件的尺寸描述对象
     */
    public void setSize(View view, CGSize size) {
        setWidth(view, size.width);
        setHeight(view, size.height);
    }

    /**
     * 设置控件的矩形信息，使用矩形信息描述对象
     *
     * @param view  要设置控件矩形的控件
     * @param frame 控件的矩形信息描述对象
     */
    public void setFrame(View view, CGRect frame) {
        setSize(view, frame.size);
        setOrigin(view, frame.origin);
    }
}