package net.lemonsoft.lemonkit4android.example;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;

import net.lemonsoft.lemonkit.model.LKIndexPath;
import net.lemonsoft.lemonkit.ui.view.LKUITableView;
import net.lemonsoft.lemonkit.ui.view.LKUITableViewCell;

/**
 * Created by lemonsoft on 16-9-27.
 */

public class TableViewTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setX(0);
        relativeLayout.setY(0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        setContentView(relativeLayout, layoutParams);

        LKUITableView tableView = new LKUITableView(this);
        tableView.setLayoutParams(layoutParams);
        tableView.setLayoutParams(layoutParams);
        relativeLayout.addView(tableView);

        tableView.dataSource = new LKUITableView.DataSource() {
            @Override
            public Integer numberOfRowsInSection(LKUITableView tableView, Integer section) {
                return 3;
            }

            @Override
            public LKUITableViewCell cellForRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath) {
                LKUITableViewCell cell = new LKUITableViewCell(TableViewTestActivity.this);
                if (indexPath.row == 1)
                    cell.setBackgroundColor(Color.CYAN);
                return cell;

            }

            @Override
            public Integer numberOfSectionsInTableView(LKUITableView tableView) {
                return 3;
            }

            @Override
            public String titleForHeaderInSection(LKUITableView tableView, Integer section) {
                return "这是第" + section + "个sectionHeader";
            }

            @Override
            public String titleForFooterInSection(LKUITableView tableView, Integer section) {
                return "这是第" + section + "个sectionFooter";
            }
        };

        tableView.delegate = new LKUITableView.Delegate() {
            @Override
            public Integer heightForRowAtIndexPath(LKUITableView tableView, LKIndexPath indexPath) {
                return 400;
            }

            @Override
            public Integer heightForHeaderInSection(LKUITableView tableView, Integer section) {
                return 60;
            }

            @Override
            public Integer heightForFooterInSection(LKUITableView tableView, Integer section) {
                return 80;
            }
        };
    }
}
