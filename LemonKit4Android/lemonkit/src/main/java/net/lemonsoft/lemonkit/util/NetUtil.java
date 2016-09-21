package net.lemonsoft.lemonkit.util;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import net.lemonsoft.lemonkit.delegate.NetUtilResultDelegate;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 1em0nsOft on 2016/9/18.
 */
public class NetUtil {

    private static final MediaType MEDIA_TYPE_URL_ENCODED = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

    private static OkHttpClient client = new OkHttpClient();

    /**
     * 异步进行httpGet请求
     *
     * @param url      要请求的URL
     * @param delegate 请求的结果代理
     */
    public static void aGet(String url, final NetUtilResultDelegate delegate) {
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                delegate.onFailed(e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                delegate.onSuccess(response.body().string());
            }
        });
    }

    /**
     * 异步执行httpPost请求
     *
     * @param url      要请求的URL
     * @param params   要请求携带的参数
     * @param delegate 请求的结果代理
     */
    public static void aPost(String url, HashMap<String, String> params, final NetUtilResultDelegate delegate) {
        try {
            StringBuilder paramsBuilder = new StringBuilder();
            for (String key : params.keySet()) {
                if (paramsBuilder.length() > 0) {
                    paramsBuilder.append("&");
                }
                paramsBuilder.append(String.format("%s=%s", key, URLEncoder.encode(params.get(key), "UTF-8")));
            }
            RequestBody requestBody = RequestBody.create(MEDIA_TYPE_URL_ENCODED, paramsBuilder.toString());
            Request request = new Request.Builder().url(url).post(requestBody).build();
            final Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    delegate.onFailed(e);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    delegate.onSuccess(response.body().string());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步进行httpPost请求表单
     *
     * @param url      要请求的url
     * @param params   要请求的普通参数
     * @param type     请求的类型键值对map
     * @param data     请求的数据键值对map
     * @param delegate 请求的代理
     */
    public static void aPost(String url, HashMap<String, String> params, HashMap<String, String> type,
                             HashMap<String, byte[]> data, final NetUtilResultDelegate delegate) {
        try {
            MultipartBuilder builder = new MultipartBuilder();
            builder.type(MultipartBuilder.FORM);
            for (String key : params.keySet()) {
                builder.addFormDataPart(key, params.get(key));
            }
            for (String key : data.keySet()) {
                builder.addFormDataPart(key, key, RequestBody.create(MediaType.parse(type.get(key)), data.get(key)));
            }
            RequestBody body = builder.build();
            final Request request = new Request.Builder().url(url).post(body).build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    delegate.onFailed(e);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    delegate.onSuccess(response.body().string());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步进行httpPost请求表单
     *
     * @param url      要请求的url
     * @param params   要请求的普通参数
     * @param type     请求的类型键值对map
     * @param data     请求的数据键值对map
     * @param delegate 请求的代理
     */
    public static void aPost(String url, HashMap<String, String> params, String type,
                             HashMap<String, byte[]> data, final NetUtilResultDelegate delegate) {
        HashMap<String, String> types = new HashMap<>();
        for (String key : data.keySet()) {
            types.put(key, type);
        }
        aPost(url, params, types, data, delegate);
    }

    /**
     * 异步执行httpPost请求
     *
     * @param url      要请求的URL
     * @param delegate 请求的结果代理
     */
    public static void aPost(String url, final NetUtilResultDelegate delegate) {
        aPost(url, new HashMap<String, String>(), delegate);
    }

}
