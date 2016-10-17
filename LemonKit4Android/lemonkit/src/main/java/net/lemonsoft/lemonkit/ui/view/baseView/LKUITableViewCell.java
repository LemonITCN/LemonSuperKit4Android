package net.lemonsoft.lemonkit.ui.view.baseView;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by LiuRi on 16/8/3.
 */
public class LKUITableViewCell extends RelativeLayout {

    private RelativeLayout contentView;

    private String reuseIdentifier;

    public LKUITableViewCell(Context context) {
        super(context);
        initCell();

    }

    public void initCell(){
        this.setBackgroundColor(Color.WHITE);
    }

}
