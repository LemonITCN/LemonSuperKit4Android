package net.lemonsoft.lemonkit.ui.view.baseView;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * LKUITableViewCell的行元素
 * Created by LiuRi on 16/8/3.
 */
public class LKUITableViewCell extends RelativeLayout {

    /**
     * 服用标识
     */
    private String reuseIdentifier = null;

    public LKUITableViewCell(Context context) {
        super(context);
        initCell();

    }

    public LKUITableViewCell(Context context, String reuseIdentifier) {
        super(context);
        this.reuseIdentifier = reuseIdentifier;
        initCell();
    }

    public void initCell() {
        this.setBackgroundColor(Color.WHITE);
    }

    public String getReuseIdentifier() {
        return reuseIdentifier;
    }
}
