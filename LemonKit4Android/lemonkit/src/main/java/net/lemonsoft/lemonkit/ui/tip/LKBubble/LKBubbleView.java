package net.lemonsoft.lemonkit.ui.tip.LKBubble;

import android.content.Context;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * LK泡泡控件
 * Created by lemonsoft on 16-10-23.
 */
public class LKBubbleView {

    private Context context;

    private PopupWindow root;
    private ImageView iconImageView;
    private TextView titleTextView;
    private boolean isShowing;
    private Map<String, LKBubbleInfo> infoMap;
    private LKBubbleInfo currentInfo;

    public LKBubbleView(Context context) {
        this.context = context;

    }

}
