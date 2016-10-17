package net.lemonsoft.lemonkit.ui.core;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

/**
 * Created by lemonsoft on 16-10-12.
 */

public class LKUIRoot extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager manager = getWindowManager();
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
    }
}
