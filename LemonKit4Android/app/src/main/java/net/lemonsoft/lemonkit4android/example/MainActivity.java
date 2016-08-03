package net.lemonsoft.lemonkit4android.example;

import android.os.Bundle;

import net.lemonsoft.lemonkit4android.R;
import net.lemonsoft.lemonkit4android.ui.core.LKUIWindow;

public class MainActivity extends LKUIWindow {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
