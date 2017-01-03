package net.lemonsoft.lemonkit.core_native_tool;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * LemonKit Drawable工具类
 * Created by LiuRi on 2016/12/30.
 */

public class LKDrawableTool {

    /**
     * 单例对象
     */
    private static LKDrawableTool _defaultDrawableTool;

    /**
     * 单例方法，获取Drawable工具类
     */
    public static LKDrawableTool getDefaultDrawableTool() {
        if (_defaultDrawableTool == null)
            _defaultDrawableTool = new LKDrawableTool();
        return _defaultDrawableTool;
    }

    /**
     * 将drawable绘制成bitmap
     *
     * @param drawable 要绘制成bitmap的drawable
     * @return 绘制完毕的bitmap对象
     */
    public Bitmap drawableToBitmap(Drawable drawable) {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        //注意，下面三行代码要用到，否在在View或者SurfaceView里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

}
