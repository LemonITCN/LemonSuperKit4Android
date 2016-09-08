package net.lemonsoft.lemonkit4android.example;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.lemonsoft.lemonkit4android.R;
import net.lemonsoft.lemonkit4android.ui.core.LKUIWindow;
import net.lemonsoft.lemonkit4android.ui.tip.LKNotification.LKNotification;

public class MainActivity extends LKUIWindow {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.showLKNotification);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LKNotification.setDefault_icon(getApplicationContext(), R.mipmap.icon);
                LKNotification.show(MainActivity.this, "这是一个LKNotification", "这是一个LKNotification的通知。正在进行测试显示。这个控件还有对应的iOS版本哦！");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
