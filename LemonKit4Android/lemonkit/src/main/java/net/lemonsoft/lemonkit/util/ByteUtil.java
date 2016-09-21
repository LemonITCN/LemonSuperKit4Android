package net.lemonsoft.lemonkit.util;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Created by 1em0nsOft on 2016/9/18.
 */
public class ByteUtil {

    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        bitmap.recycle();
        byte[] result = outputStream.toByteArray();
        try {
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
