package net.lemonsoft.lemonkit4android.example;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import net.lemonsoft.lemonkit.ui.core.LKUIWindow;
import net.lemonsoft.lemonkit.ui.tip.LKActionSheet.LKActionItem;
import net.lemonsoft.lemonkit.ui.tip.LKActionSheet.LKActionSheetView;
import net.lemonsoft.lemonkit.util.SizeUtil;
import net.lemonsoft.lemonkit4android.R;

public class MainActivity extends LKUIWindow {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.showLKNotification);
        final ImageView imageView = (ImageView) findViewById(R.id.tempImageView);
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LKNotification.setDefault_icon(getApplicationContext(), R.mipmap.icon);
//                LKNotification.create(MainActivity.this, "这是一个LKNotification", "这是一个LKNotification的通知。正在进行测试显示。这个控件还有对应的iOS版本哦！")
//                        .show(new LKNotificationStateChangeAdapter() {
//                            @Override
//                            public void onHideComplete() {
//                                super.onHideComplete();
//                                System.out.println("HIDE@@@@@@@@@@@@@@@@@@@@@ ");
//                            }
//                        });
                final LKActionSheetView as = new LKActionSheetView(MainActivity.this);
                as.setHeadViewHeight(SizeUtil.dipToPx(getApplicationContext(), 40));
                as.clear();
                as.setTitle("这是一个LKActionSheet视图，还有iOS版本呢！");
                as.addActionGroup();
                as.addAction(new LKActionItem(getApplicationContext(), "取消", Color.RED, new LKActionItem.OnItemTouchDelegate() {
                    @Override
                    public void onItemTouchAction() {
                        as.hide();
                    }
                }), 0);
                as.addAction(new LKActionItem(getApplicationContext(), "哈哈"), 1);
                as.addAction(new LKActionItem(getApplicationContext(), "哈哈哈"), 1);
                as.show();
            }
        });
    }

}
