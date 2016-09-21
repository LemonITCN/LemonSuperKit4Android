package net.lemonsoft.lemonkit.delegate;

import com.squareup.okhttp.Response;

/**
 * Created by 1em0nsOft on 2016/9/18.
 */
public interface NetUtilResultDelegate {

    void onSuccess(String data);

    void onFailed(Exception exception);

}
