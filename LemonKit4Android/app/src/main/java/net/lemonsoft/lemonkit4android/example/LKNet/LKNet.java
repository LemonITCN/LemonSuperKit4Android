package net.lemonsoft.lemonkit4android.example.LKNet;

import android.graphics.Bitmap;

import net.lemonsoft.lemonkit.delegate.NetUtilResultDelegate;
import net.lemonsoft.lemonkit.util.ByteUtil;
import net.lemonsoft.lemonkit.util.EncodeTool;
import net.lemonsoft.lemonkit.util.NetUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 1em0nsOft on 2016/9/18.
 */
public class LKNet {

    /**
     * 上传文件
     *
     * @param url      要上传文件的url
     * @param params   携带的键值对参数
     * @param type     上传文件的统一类型
     * @param token    上传时候的accessToken
     * @param bitmaps  上传人的图片list
     * @param delegate 请求处理代理
     */
    public static void aUploadFiles(String url, HashMap<String, String> params, String type, String token,
                                    ArrayList<Bitmap> bitmaps, NetUtilResultDelegate delegate) {
        try {
            HashMap<String, byte[]> data = new HashMap<>();
            for (int i = 0; i < bitmaps.size(); i++) {
                String key = String.format("dlfc_%s_%d", token, i);
                byte[] bitmapByteArr = ByteUtil.bitmapToByteArray(bitmaps.get(i));
                data.put(String.format("%s_%d",
                        EncodeTool.byteArrayToHexString(
                                EncodeTool.encodeHmacMD5(bitmapByteArr, key)
                        ).toUpperCase()
                        , i), bitmapByteArr);
            }
            NetUtil.aPost(url, params, type, data, delegate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
