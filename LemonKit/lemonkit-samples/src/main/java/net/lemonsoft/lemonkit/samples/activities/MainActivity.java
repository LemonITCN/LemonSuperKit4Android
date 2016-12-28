package net.lemonsoft.lemonkit.samples.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout relativeLayout = new RelativeLayout(getApplicationContext());
//        setContentView(R.layout.activity_main);
        setContentView(relativeLayout);
        TextView textView = new TextView(getApplicationContext());
        textView.setBackgroundColor(Color.RED);
        textView.setLayoutParams(new RelativeLayout.LayoutParams(300, 300));
        relativeLayout.addView(textView);

    }
}
