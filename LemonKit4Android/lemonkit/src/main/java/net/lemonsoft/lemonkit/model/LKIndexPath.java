package net.lemonsoft.lemonkit.model;

import android.widget.RelativeLayout;

/**
 * Created by lemonsoft on 16-9-27.
 */

public class LKIndexPath {

    public Integer section;
    public Integer row;

    public LKIndexPath(Integer section, Integer row) {
        this.section = section;
        this.row = row;
    }

    public static LKIndexPath make(Integer section, Integer row) {
        return new LKIndexPath(section, row);
    }

}
