package net.lemonsoft.lemonkit4android.example;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;

import net.lemonsoft.lemonkit.adapter.LKUITableView.LKUITableViewDataSourceAdapter;
import net.lemonsoft.lemonkit.adapter.LKUITableView.LKUITableViewDelegateAdapter;
import net.lemonsoft.lemonkit.model.LKIndexPath;
import net.lemonsoft.lemonkit.ui.core.LKUIRoot;
import net.lemonsoft.lemonkit.ui.view.baseView.LKUITableView;
import net.lemonsoft.lemonkit.ui.view.baseView.LKUITableViewCell;
import net.lemonsoft.lemonkit.ui.view.baseView.LKUIView;

/**
 * Created by lemonsoft on 16-9-27.
 */

public class TableViewTestActivity extends LKUIRoot {

    LKUIView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new LKUIView(TableViewTestActivity.this);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setX(0);
        relativeLayout.setY(0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        setContentView(relativeLayout, layoutParams);

        LKUITableView tableView = new LKUITableView(this);
        tableView.setLayoutParams(layoutParams);
        tableView.setLayoutParams(layoutParams);
        relativeLayout.addView(tableView);
        tableView.setBackgroundColor(Color.argb(255, 238, 238, 238));

        tableView.delegate = new LKUITableViewDelegateAdapter() {
            @Override
            public void didSelectRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath) {
                System.out.println("卧槽，被点急了：" + indexPath.section + " r : " + indexPath.row);
                if (indexPath.row == 0)
                    view.setBorderWidth(1);
            }
        };

        tableView.dataSource = new LKUITableViewDataSourceAdapter() {
            @Override
            public Integer numberOfRowsInSection(LKUITableView tableView, Integer section) {
                return 30;
            }

            @Override
            public LKUITableViewCell cellForRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath) {
                String identifier = String.format("%d_%d", indexPath.section, indexPath.row);
                LKUITableViewCell cell = tableView.dequeueReusableCellWithIdentifier(identifier);
                if (cell == null) {// 复用机制里面没有，新创建
                    cell = new LKUITableViewCell(TableViewTestActivity.this , identifier);
                    cell.setBackgroundColor(Color.argb(255, (int) (Math.random() * 255.0), (int) (Math.random() * 255.0), (int) (Math.random() * 255.0)));
                }
                if (indexPath.row == 0) {
                    cell.removeAllViews();
//                    cell.addView(view);
                }
                return cell;
            }
        };
    }
}
