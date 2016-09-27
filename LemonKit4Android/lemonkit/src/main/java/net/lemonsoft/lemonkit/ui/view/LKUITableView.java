package net.lemonsoft.lemonkit.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import net.lemonsoft.lemonkit.model.LKIndexPath;

/**
 * Created by lemonsoft on 16-9-27.
 */
public class LKUITableView extends ScrollView {

    private Context context;

    public DataSource dataSource;
    public Delegate delegate;

    private RelativeLayout contentView;

    public LKUITableView(Context context) {
        super(context);
        this.context = context;

        this.contentView = new RelativeLayout(this.context);
        this.addView(this.contentView);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        reloadData();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        reloadData();
    }

    public void reloadData() {
        if (dataSource != null) {
            // 指定了dataSOUrce
            Integer sectionCount = dataSource.numberOfSectionsInTableView(this);
            Integer contentHeight = 0;
            for (int si = 0; si < sectionCount; si++) {
                Integer rowCount = dataSource.numberOfRowsInSection(this, si);
                contentHeight += delegate.heightForHeaderInSection(this, si);
                for (int ri = 0; ri < rowCount; ri++) {
                    contentHeight += delegate.heightForRowAtIndexPath(this, LKIndexPath.make(si, ri));
                }
                contentHeight += delegate.heightForFooterInSection(this, si);
            }
            initContentView(contentHeight);
        }
    }

    public void initContentView(Integer contentHeight) {
        this.contentView.setX(0);
        this.contentView.setY(0);
        FrameLayout.LayoutParams contentViewLayoutParams = new FrameLayout.LayoutParams(getWidth(), contentHeight);
        this.contentView.setLayoutParams(contentViewLayoutParams);
        this.contentView.setBackgroundColor(Color.GREEN);
    }

    public interface DataSource {
        // required
        Integer numberOfRowsInSection(LKUITableView tableView, Integer section);

        LKUITableViewCell cellForRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath);

        // optional
        Integer numberOfSectionsInTableView(LKUITableView tableView);

        String titleForHeaderInSection(LKUITableView tableView, Integer section);

        String titleForFooterInSection(LKUITableView tableView, Integer section);

    }

    public interface Delegate {
        // Variable height support
        Integer heightForRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath);

        Integer heightForHeaderInSection(LKUITableView tableView, Integer section);

        Integer heightForFooterInSection(LKUITableView tableView, Integer section);
    }

}
