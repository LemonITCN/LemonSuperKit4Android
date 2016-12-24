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

    /**
     * 两个LKIndexPath判断相等
     *
     * @param indexPath 要比较的另外一个indexPath
     * @return 是否一致的布尔值
     */
    public boolean equals(LKIndexPath indexPath) {
        if (indexPath == null)
            return false;
        return section == indexPath.section && row == indexPath.row;
    }
}
