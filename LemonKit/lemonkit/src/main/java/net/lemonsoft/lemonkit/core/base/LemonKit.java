package net.lemonsoft.lemonkit.core.base;

import android.app.Application;
import android.content.Context;

import net.lemonsoft.lemonkit.native_ui.tools.LKSizeTool;

/**
 * LemonKit核心类
 * Created by LiuRi on 2016/12/24.
 */

public class LemonKit {

    // 实例对象
    private static LemonKit _instance;
    // 应用程序对象
    private Application application;

    /**
     * 获取LemonKit的单例对象
     */
    public static synchronized LemonKit instance() {
        if (_instance == null)
            _instance = new LemonKit();
        return _instance;
    }

    /**
     * 隐藏构造方法
     */
    private LemonKit() {
    }

    /**
     * 初始化方法
     * 需要在应用程序的onCreate方法中调用此方法来初始化LemonKit
     *
     * @param application 应用对象
     */
    public void init(Application application) {
        this.application = application;
        // 初始化LemonKit尺寸工具类
        LKSizeTool.getDefaultSizeTool().setContext(this.getAppContext());
    }

    /**
     * 获取整个应用程序的上下文对象
     *
     * @return 应用程序的上下文对象
     */
    public Context getAppContext() {
        return this.application.getApplicationContext();
    }

}
